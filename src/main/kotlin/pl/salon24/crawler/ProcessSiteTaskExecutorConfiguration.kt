package pl.salon24.crawler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class ProcessSiteTaskExecutorConfiguration {
    companion object {
        private val POOL_SIZE = Runtime.getRuntime().availableProcessors()
    }

    @Bean
    fun processSiteTaskExecutor(): ThreadPoolTaskExecutor =
            ThreadPoolTaskExecutor().apply {
                corePoolSize = POOL_SIZE
                maxPoolSize = POOL_SIZE
                setThreadNamePrefix("parse-site-thread-")
                setAwaitTerminationSeconds(1)
                initialize()
            }
}