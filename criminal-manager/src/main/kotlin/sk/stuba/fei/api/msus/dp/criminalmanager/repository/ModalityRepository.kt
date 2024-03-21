package sk.stuba.fei.api.msus.dp.criminalmanager.repository

import org.springframework.data.mongodb.repository.MongoRepository
import sk.stuba.fei.api.msus.dp.criminalmanager.model.ModalityEntity

interface ModalityRepository : MongoRepository<ModalityEntity, String> {

    fun findAllByCriminalId(criminalId: String): List<ModalityEntity>

    fun deleteAllByCriminalId(criminalId: String)
}