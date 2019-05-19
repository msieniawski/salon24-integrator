package pl.salon24.userpersister

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import pl.salon24.model.entity.User
import pl.salon24.model.repository.UserRepository

@Component
class UserPersister(
        private val userRepository: UserRepository,
        private val userPersisterTaskExecutor: ThreadPoolTaskExecutor
) {

    fun save(users: Iterable<User>) {
        userPersisterTaskExecutor.execute(PersistUserTask(users))
    }

    fun save(user: User) {
        userPersisterTaskExecutor.execute(PersistUserTask(user))
    }

    private inner class PersistUserTask(private val users: Iterable<User>) : Runnable {
        constructor(user: User) : this(listOf(user))

        override fun run() {
            userRepository.saveAll(users)
        }
    }
}

