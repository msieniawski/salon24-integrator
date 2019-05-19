package pl.salon24.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import pl.salon24.model.entity.ProcessedSite

@RepositoryRestResource(collectionResourceRel = "PROCESSED_SITES", path = "processed-sites")
interface ProcessedSiteRepository : JpaRepository<ProcessedSite, String> {

    fun existsByUrl(url: String): Boolean
}