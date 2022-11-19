package com.zijian

import mu.KLogger
import mu.KotlinLogging

class LoggingServiceImpl(
    private val logger: KLogger = KotlinLogging.logger {}
) : LoggingService {

    lateinit var target: String

    override fun log() {
        logger.info { "testing logging $target" }
    }

}