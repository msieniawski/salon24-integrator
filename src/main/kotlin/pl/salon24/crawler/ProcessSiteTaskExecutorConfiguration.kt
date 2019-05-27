package pl.salon24.crawler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class ProcessSiteTaskExecutorConfiguration {
    companion object {
        private const val POOL_SIZE = 32
    }

    @Bean
    fun processSiteTaskExecutor(): ThreadPoolTaskExecutor =
            ThreadPoolTaskExecutor().apply {
                corePoolSize = POOL_SIZE
                maxPoolSize = POOL_SIZE
                threadNamePrefix = "parse-site-thread-"
                setAwaitTerminationSeconds(1)
                initialize()
            }
}