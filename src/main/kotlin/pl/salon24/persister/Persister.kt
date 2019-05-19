package pl.salon24.persister

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import pl.salon24.utils.logger

abstract class Persister<T, ID>(
        private val repository: JpaRepository<T, ID>,
        private val taskExecutor: ThreadPoolTaskExecutor
) {
    private val log by logger()

    fun persist(ts: Iterable<T>) {
        taskExecutor.execute(PersistTask(ts))
    }

    fun persist(t: T) {
        taskExecutor.execute(PersistTask(t))
    }

    private inner class PersistTask(private val ts: Iterable<T>) : Runnable {
        constructor(t: T) : this(listOf(t))

        override fun run() {
            log.warn("Persisting... Left: ${taskExecutor.threadPoolExecutor.queue.size}")
            repository.saveAll(ts)
        }
    }
}