package pl.salon24.persister

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import pl.salon24.model.entity.ProcessedSite
import pl.salon24.model.repository.ProcessedSiteRepository

@Component
class ProcessedSitePersister(
        processedSiteRepository: ProcessedSiteRepository,
        persisterTaskExecutor: ThreadPoolTaskExecutor
) : Persister<ProcessedSite, String>(processedSiteRepository, persisterTaskExecutor)