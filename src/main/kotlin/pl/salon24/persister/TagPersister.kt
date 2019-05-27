package pl.salon24.persister

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import pl.salon24.model.entity.Tag
import pl.salon24.model.repository.TagRepository

@Component
class TagPersister(
        tagRepository: TagRepository,
        persisterTaskExecutor: ThreadPoolTaskExecutor
) : Persister<Tag, String>(tagRepository, persisterTaskExecutor)