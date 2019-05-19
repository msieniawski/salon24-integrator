package pl.salon24.persister

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class PersisterConfiguration {
    companion object {
        private const val POOL_SIZE = 1
    }

    @Bean
    fun persisterTaskExecutor(): ThreadPoolTaskExecutor =
            ThreadPoolTaskExecutor().apply {
                corePoolSize = POOL_SIZE
                maxPoolSize = POOL_SIZE
                threadNamePrefix = "persister-"
                setAwaitTerminationSeconds(1)
                initialize()
            }
}