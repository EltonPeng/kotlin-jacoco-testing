package com.zijian

import io.mockk.*
import io.mockk.impl.annotations.MockK
import mu.KLogger

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class LoggingServiceTest {
    @MockK
    private lateinit var logger: KLogger

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun log() {
        val slot = slot<() -> Any?>()
        every { logger.info(capture(slot)) } just Runs
        val loggingService = LoggingService(logger)
        loggingService.target = "haihaihai"

        loggingService.log()

        assertEquals("testing logging haihaihai", slot.captured.invoke())
    }
}