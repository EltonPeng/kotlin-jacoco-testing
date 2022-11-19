package com.zijian

import io.mockk.*
import io.mockk.impl.annotations.MockK
import mu.KLogger

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class LoggingServiceImplTest {
    @MockK
    private lateinit var logger: KLogger

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `cover constructors`() {
        val constructors = LoggingServiceImpl::class.java.constructors
        assertEquals(3, constructors.count())
        constructors.forEach {
            if(it.parameters.isEmpty()) {
                assertNotNull(it.newInstance())
            } else if(it.parameters.count() == 1){
                assertNotNull(it.newInstance(logger))
            }
            else {
                assertNotNull(it.newInstance(logger, 0, null))
                assertNotNull(it.newInstance(logger, 1, null))
            }
        }
    }

    @Test
    fun log() {
        val slot = slot<() -> Any?>()
        every { logger.info(capture(slot)) } just Runs
        val loggingServiceImpl = LoggingServiceImpl(logger)
        loggingServiceImpl.target = "haihaihai"

        loggingServiceImpl.log()

        assertEquals("testing logging haihaihai", slot.captured.invoke())
    }
}