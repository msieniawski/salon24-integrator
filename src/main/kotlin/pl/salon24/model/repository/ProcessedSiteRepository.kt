package pl.salon24.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.salon24.model.entity.ProcessedSite

interface ProcessedSiteRepository : JpaRepository<ProcessedSite, String> {

    fun existsByUrl(url: String): Boolean
}