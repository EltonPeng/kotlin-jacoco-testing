package com.zijian

import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach

internal class EntranceTest {
    @MockK
    lateinit var loggingService: LoggingService

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun start() {
        every { loggingService.log() } just Runs

        val entrance = Entrance()
        entrance.loggingService = loggingService

        entrance.start()

        verify { loggingService.log() }
    }
}