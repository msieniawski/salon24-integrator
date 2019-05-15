package pl.salon24.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <T : Any> T.logger(): Lazy<Logger> = logger(javaClass)

fun <T : Any> T.logger(clazz: Class<*>): Lazy<Logger> = logger(clazz.name)

fun <T : Any> T.logger(name: String): Lazy<Logger> = lazy { LoggerFactory.getLogger(name) }