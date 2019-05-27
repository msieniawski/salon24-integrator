package pl.salon24.persister

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

abstract class Persister<T, ID>(
        private val repository: JpaRepository<T, ID>,
        private val taskExecutor: ThreadPoolTaskExecutor
) {

    fun persist(ts: Iterable<T>) {
        taskExecutor.execute(PersistTask(ts))
    }

    fun persist(t: T) {
        taskExecutor.execute(PersistTask(t))
    }

    private inner class PersistTask(private val ts: Iterable<T>) : Runnable {
        constructor(t: T) : this(listOf(t))

        override fun run() {
            try {
                repository.saveAll(ts)
            } catch (e: DataIntegrityViolationException) {

            }
        }
    }
}