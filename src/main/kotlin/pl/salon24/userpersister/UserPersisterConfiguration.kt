package pl.salon24.userpersister

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class UserPersisterConfiguration {
    companion object {
        private const val POOL_SIZE = 1
    }

    @Bean
    fun userPersisterTaskExecutor(): ThreadPoolTaskExecutor =
            ThreadPoolTaskExecutor().apply {
                corePoolSize = POOL_SIZE
                maxPoolSize = POOL_SIZE
                setThreadNamePrefix("user-persister-thread--")
                setAwaitTerminationSeconds(1)
                initialize()
            }
}