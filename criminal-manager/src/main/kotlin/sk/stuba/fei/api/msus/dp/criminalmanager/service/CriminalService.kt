package sk.stuba.fei.api.msus.dp.criminalmanager.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityEntity
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType
import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.CriminalResponseDto
import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.ModalityRequestDto
import sk.stuba.fei.api.msus.dp.criminalmanager.payload.request.AddModalitiesRequest
import sk.stuba.fei.api.msus.dp.criminalmanager.payload.request.CreateCriminalRequest
import sk.stuba.fei.api.msus.dp.criminalmanager.payload.response.GetAllCriminalsResponse
import sk.stuba.fei.api.msus.dp.criminalmanager.payload.response.GetCriminalModalitiesResponse
import sk.stuba.fei.api.msus.dp.criminalmanager.payload.response.GetCriminalResponse
import sk.stuba.fei.api.msus.dp.criminalmanager.payload.response.MessageResponse
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

        return ResponseEntity.ok(
            GetCriminalResponse(
                criminal = if (criminal.isPresent) {
                    criminal.get().toResponseDto()
                } else {
                    null
                },
                modalities = if (criminal.isPresent and withModalities) {
                    modalityRepository.findAllByCriminalId(id).map { it.toResponseDto() }
                } else {
                    null
                }
            )
        )
    }

    fun getAllCriminals(): ResponseEntity<GetAllCriminalsResponse> =
        ResponseEntity.ok(
            GetAllCriminalsResponse(
                criminals = criminalRepository.findAll().map { it.toResponseDto() }
            )
        )

    fun createCriminal(createCriminalRequest: CreateCriminalRequest): ResponseEntity<CriminalResponseDto> {
        val savedCriminal = criminalRepository.save(createCriminalRequest.criminal.toEntity())
        saveModalitiesForCriminal(savedCriminal.id!!, createCriminalRequest.modalities)

        return ResponseEntity.ok(savedCriminal.toResponseDto())
    }

    fun deleteAll(): ResponseEntity<MessageResponse> {
        criminalRepository.deleteAll()
        modalityRepository.deleteAll()

        return ResponseEntity.ok(MessageResponse("Deleted all criminals and modalities"))
    }

    fun deleteCriminalById(id: String) = if (criminalRepository.existsById(id)) {
        criminalRepository.deleteById(id)
        modalityRepository.deleteAllByCriminalId(id)

        ResponseEntity.ok(MessageResponse("Criminal with ID '$id' deleted along with their modalities"))
    } else {
        ResponseEntity.badRequest().body(MessageResponse("Criminal with ID '$id' does not exist"))
    }

    fun getModalitiesForCriminal(criminalId: String): ResponseEntity<GetCriminalModalitiesResponse> =
        ResponseEntity.ok(
            GetCriminalModalitiesResponse(
                criminalId = criminalId,
                modalities = modalityRepository.findAllByCriminalId(criminalId).map { it.toResponseDto() }
            )
        )

    fun addModalitiesForCriminal(
        criminalId: String,
        modalitiesRequest: AddModalitiesRequest
    ): ResponseEntity<MessageResponse> =
        if (criminalRepository.existsById(criminalId)) {
            saveModalitiesForCriminal(criminalId, modalitiesRequest.modalities)

            ResponseEntity.ok(MessageResponse("Modalities added for criminal with ID '$criminalId'"))
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

    private fun saveModalitiesForCriminal(criminalId: String, modalities: List<ModalityRequestDto>) {
        modalities.map {
            when (it.type) {
                ModalityType.FINGERPRINT -> createFingerprintModalityEntity(criminalId, it.data)
                ModalityType.IRIS -> createIrisModalityEntity(criminalId, it.data)
                else ->
                    ModalityEntity(
                        criminalId = criminalId,
                        type = it.type,
                        rawData = it.data,
                        keypointsSize = 0,
                        encodedDescriptor = ""
                    )
            }
        }.run { modalityRepository.saveAll(this) }
    }

    private fun createFingerprintModalityEntity(criminalId: String, data: String): ModalityEntity {
        val extractionResponse = featureExtractorClient.extractFingerprint(
            FeatureExtractionRequest.newBuilder()
                .setData(data)
                .build()
        )

        return ModalityEntity(
            criminalId = criminalId,
            rawData = data,
            type = ModalityType.FINGERPRINT,
            keypointsSize = extractionResponse.keypointsSize,
            encodedDescriptor = extractionResponse.encodedDescriptor
        )
    }

    private fun createIrisModalityEntity(criminalId: String, data: String): ModalityEntity {
        val extractionResponse = featureExtractorClient.extractIris(
            FeatureExtractionRequest.newBuilder()
                .setData(data)
                .build()
        )

        return ModalityEntity(
            criminalId = criminalId,
            rawData = data,
            type = ModalityType.IRIS,
            keypointsSize = extractionResponse.keypointsSize,
            encodedDescriptor = extractionResponse.encodedDescriptor
        )
    }
}