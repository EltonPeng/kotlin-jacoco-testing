package com.zijian

import mu.KLogger
import mu.KotlinLogging

class LoggingService(
    private val logger: KLogger = KotlinLogging.logger {}
) {

    lateinit var target: String

    fun log() {
        logger.info { "testing logging $target" }
    }

}