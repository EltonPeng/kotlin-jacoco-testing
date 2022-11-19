package com.zijian

import jakarta.enterprise.context.ApplicationScoped
import mu.KLogger
import mu.KotlinLogging

@ApplicationScoped
class LoggingServiceImpl(
    private val logger: KLogger = KotlinLogging.logger {}
) : LoggingService {

    lateinit var target: String

    override fun log() {
        logger.info { "testing logging $target" }
    }

}