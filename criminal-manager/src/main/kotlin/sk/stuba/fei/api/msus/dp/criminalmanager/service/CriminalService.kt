package sk.stuba.fei.api.msus.dp.criminalmanager.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType.FINGERPRINT
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType.IRIS
import sk.stuba.fei.api.msus.dp.criminalmanager.model.entity.FingerprintModalityEntity
import sk.stuba.fei.api.msus.dp.criminalmanager.model.entity.IModalityEntity
import sk.stuba.fei.api.msus.dp.criminalmanager.model.entity.IrisModalityEntity
import sk.stuba.fei.api.msus.dp.criminalmanager.model.exceptions.UnsupportedModalityTypeException
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request.AddModalitiesRequest
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request.CreateCriminalRequest
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request.dto.FingerprintModalityRequestDto
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request.dto.IModalityRequestDto
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.request.dto.IrisModalityRequestDto
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.GetAllCriminalsResponse
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.GetCriminalModalitiesResponse
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.GetCriminalResponse
import sk.stuba.fei.api.msus.dp.criminalmanager.model.payload.response.MessageResponse
import sk.stuba.fei.api.msus.dp.criminalmanager.repository.CriminalRepository
import sk.stuba.fei.api.msus.dp.criminalmanager.repository.ModalityRepository
import sk.stuba.fei.api.msus.dp.featureextractor.FeatureExtractionRequest

@Service
class CriminalService(
    private val criminalRepository: CriminalRepository,
    private val modalityRepository: ModalityRepository,
    private val featureExtractorClient: FeatureExtractorClient
) {
    fun getCriminalById(id: String, withModalities: Boolean): ResponseEntity<GetCriminalResponse> {
        val criminal = criminalRepository.findById(id)

        return if (criminal.isEmpty) {
            ResponseEntity.ok(GetCriminalResponse())
        } else {
            ResponseEntity.ok(
                GetCriminalResponse(criminal = criminal.get().toResponseDto())
                    .apply {
                        if (withModalities) {
                            modalities = modalityRepository.findAllByCriminalId(id).map { it.toResponseDto() }
                        }
                    }
            )
        }
    }

    fun getAllCriminals(): ResponseEntity<GetAllCriminalsResponse> =
        ResponseEntity.ok(
            GetAllCriminalsResponse(
                criminals = criminalRepository.findAll().map { it.toResponseDto() }
            )
        )

    fun createCriminal(request: CreateCriminalRequest): ResponseEntity<out Any> {
        val savedCriminal = criminalRepository.save(request.criminal.toEntity())
        try {
            saveModalitiesForCriminal(savedCriminal.id!!, request.modalities)
            return ResponseEntity.ok(savedCriminal.toResponseDto())
        } catch (ex: UnsupportedModalityTypeException) {
            return ResponseEntity.badRequest().body(MessageResponse(ex.message))
        }
    }

    fun deleteAll(): ResponseEntity<MessageResponse> {
        criminalRepository.deleteAll()
        modalityRepository.deleteAll()
        return ResponseEntity.ok(MessageResponse("Deleted all criminals and modalities"))
    }

    fun deleteCriminalById(id: String): ResponseEntity<MessageResponse> =
        if (criminalRepository.existsById(id)) {
            criminalRepository.deleteById(id)
            modalityRepository.deleteAllByCriminalId(id)
            ResponseEntity.ok(MessageResponse("Criminal with ID '$id' deleted along with their modalities"))
        } else {
            ResponseEntity.badRequest().body(MessageResponse("Criminal with ID '$id' does not exist"))
        }

    fun getModalitiesForCriminal(criminalId: String): ResponseEntity<out Any> =
        if (criminalRepository.existsById(criminalId)) {
            ResponseEntity.ok(
                GetCriminalModalitiesResponse(
                    criminalId = criminalId,
                    modalities = modalityRepository.findAllByCriminalId(criminalId).map { it.toResponseDto() }
                )
            )
        } else {
            ResponseEntity.badRequest().body(MessageResponse("Criminal with ID '$criminalId' does not exist"))
        }

    fun addModalitiesForCriminal(
        criminalId: String,
        modalitiesRequest: AddModalitiesRequest
    ): ResponseEntity<MessageResponse> =
        if (criminalRepository.existsById(criminalId)) {
            try {
                saveModalitiesForCriminal(criminalId, modalitiesRequest.modalities)
                ResponseEntity.ok(MessageResponse("Modalities added for criminal with ID '$criminalId'"))
            } catch (ex: UnsupportedModalityTypeException) {
                ResponseEntity.badRequest().body(MessageResponse(ex.message))
            }
        } else {
            ResponseEntity.badRequest().body(MessageResponse("Criminal with ID '$criminalId' does not exist"))
        }

    fun removeModalitiesOfCriminal(criminalId: String): ResponseEntity<MessageResponse> =
        if (criminalRepository.existsById(criminalId)) {
            modalityRepository.deleteAllByCriminalId(criminalId)
            ResponseEntity.ok(MessageResponse("Modalities removed for criminal with ID '$criminalId'"))
        } else {
            ResponseEntity.badRequest().body(MessageResponse("Criminal with ID '$criminalId' does not exist"))
        }

    fun removeModalityOfCriminal(criminalId: String, modalityId: String): ResponseEntity<MessageResponse> =
        if (criminalRepository.existsById(criminalId)) {
            if (modalityRepository.existsById(modalityId)) {
                modalityRepository.deleteById(modalityId)
                ResponseEntity.ok(MessageResponse("Modality '$modalityId' removed for criminal '$criminalId'"))
            } else {
                ResponseEntity.badRequest().body(MessageResponse("Modality with ID '$modalityId' does not exist"))
            }
        } else {
            ResponseEntity.badRequest().body(MessageResponse("Criminal with ID '$criminalId' does not exist"))
        }

    private fun saveModalitiesForCriminal(criminalId: String, modalities: List<IModalityRequestDto>) {
        modalities.map {
            when (it.type) {
                FINGERPRINT -> createFingerprintModalityEntity(criminalId, it as FingerprintModalityRequestDto)
                IRIS -> createIrisModalityEntity(criminalId, it as IrisModalityRequestDto)
                else -> throw UnsupportedModalityTypeException(message = "Unsupported modality type: ${it.type}")
            }
        }.run { modalityRepository.saveAll(this) }
    }

    private fun createFingerprintModalityEntity(
        criminalId: String,
        requestDto: FingerprintModalityRequestDto
    ): IModalityEntity {
        val extractionResponse = featureExtractorClient.extractFingerprint(
            FeatureExtractionRequest.newBuilder()
                .setRawData(requestDto.rawData)
                .build()
        )

        return FingerprintModalityEntity(
            criminalId = criminalId,
            rawData = requestDto.rawData,
            keypointsSize = extractionResponse.keypointsSize,
            encodedDescriptor = extractionResponse.encodedDescriptor,
            position = requestDto.position
        )
    }

    private fun createIrisModalityEntity(
        criminalId: String,
        requestDto: IrisModalityRequestDto
    ): IModalityEntity {
        val extractionResponse = featureExtractorClient.extractIris(
            FeatureExtractionRequest.newBuilder()
                .setRawData(requestDto.rawData)
                .build()
        )

        return IrisModalityEntity(
            criminalId = criminalId,
            rawData = requestDto.rawData,
            keypointsSize = extractionResponse.keypointsSize,
            encodedDescriptor = extractionResponse.encodedDescriptor,
            position = requestDto.position
        )
    }
}