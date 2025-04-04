package sk.stuba.fei.api.msus.dp.criminalmanager.repository

import org.springframework.data.mongodb.repository.MongoRepository
import sk.stuba.fei.api.msus.dp.criminalmanager.model.entity.IModalityEntity

interface ModalityRepository : MongoRepository<IModalityEntity, String> {

    fun findAllByCriminalId(criminalId: String): List<IModalityEntity>

    fun deleteAllByCriminalId(criminalId: String)
}