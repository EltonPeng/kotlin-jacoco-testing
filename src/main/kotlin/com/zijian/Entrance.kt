package com.zijian

import jakarta.inject.Inject

class Entrance {

    @Inject
    lateinit var loggingService: LoggingService

    fun start() {
        loggingService.log()
    }

}