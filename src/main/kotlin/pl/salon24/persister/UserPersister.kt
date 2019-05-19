package pl.salon24.persister

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import pl.salon24.model.entity.User
import pl.salon24.model.repository.UserRepository

@Component
class UserPersister(
        userRepository: UserRepository,
        persisterTaskExecutor: ThreadPoolTaskExecutor
) : Persister<User, String>(userRepository, persisterTaskExecutor)

