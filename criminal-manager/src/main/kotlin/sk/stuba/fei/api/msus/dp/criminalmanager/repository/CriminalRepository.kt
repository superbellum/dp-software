package sk.stuba.fei.api.msus.dp.criminalmanager.repository

import org.springframework.data.mongodb.repository.MongoRepository
import sk.stuba.fei.api.msus.dp.criminalmanager.model.CriminalEntity

interface CriminalRepository : MongoRepository<CriminalEntity, String> {
}