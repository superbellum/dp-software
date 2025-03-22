package sk.stuba.fei.api.msus.dp.criminalmanager.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import sk.stuba.fei.api.msus.dp.criminalmanager.model.FingerprintModalityEntity
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityEntity
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityType.FINGERPRINT
import sk.stuba.fei.api.msus.dp.criminalmanager.model.dto.CriminalResponseDto
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

        createCriminalRequest.modalities
            ?.map {
                when (it.type) {
                    FINGERPRINT -> createFingerprintModalityEntity(savedCriminal.id!!, it.data)
                    else ->
                        ModalityEntity(
                            criminalId = savedCriminal.id!!,
                            type = it.type,
                            rawData = it.data
                        )
                }
            }
            ?.run { modalityRepository.saveAll(this) }

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

    fun addModalitiesForCriminal(criminalId: String, modalitiesRequest: AddModalitiesRequest): ResponseEntity<MessageResponse> =
        if (criminalRepository.existsById(criminalId)) {
            modalitiesRequest.modalities
                .map {
                    when (it.type) {
                        FINGERPRINT -> createFingerprintModalityEntity(criminalId, it.data)
                        else ->
                            ModalityEntity(
                                criminalId = criminalId,
                                type = it.type,
                                rawData = it.data
                            )
                    }
                }
                .run { modalityRepository.saveAll(this) }

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

    private fun createFingerprintModalityEntity(criminalId: String, data: String): FingerprintModalityEntity {
        val extractionResponse = featureExtractorClient.extractFingerprint(
            FeatureExtractionRequest.newBuilder()
                .setData(data)
                .build()
        )

        return FingerprintModalityEntity(
            criminalId = criminalId,
            rawData = data,
            keypointsSize = extractionResponse.keypointsSize,
            encodedDescriptor = extractionResponse.encodedDescriptor
        )
    }
}