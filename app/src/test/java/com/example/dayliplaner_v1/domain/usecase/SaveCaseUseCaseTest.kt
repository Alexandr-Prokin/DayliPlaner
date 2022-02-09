package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepository
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.time.LocalDateTime

class SaveCaseUseCaseTest {

    private val caseRecordRepository = mock<CaseRecordRepository>()

    @Test
    fun `saving in the database`() {
        val testCaseRecord = CaseRecordModel(
            name = "name",
            description = "description",
            dateStart = LocalDateTime.of(2022, 12, 12, 12 , 12),
            dateFinish = LocalDateTime.of(2022, 12, 12, 14 , 12)
        )

        Mockito.`when`(caseRecordRepository.saveCaseRecord(caseRecordModel = testCaseRecord)).thenReturn(true)
        val useCase = SaveCaseUseCase(caseRecordRepository = caseRecordRepository)
        val actual = useCase.execute(caseRecordModel = testCaseRecord)
        val expected = true

        Assert.assertEquals(expected, actual)
    }
}
