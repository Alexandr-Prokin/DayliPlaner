package com.example.dayliplaner_v1.domain.usecase

import org.junit.Assert.*
import org.junit.Test

class GetTimeUseCaseTest {

    @Test
    fun `getTime`() {
        val testTimeStamp = "1643544000"
        val useCase = GetTimeUseCase()
        val actual = useCase.getTime(testTimeStamp)
        val expected = "15:00"
        assertEquals(expected, actual)
    }
}
