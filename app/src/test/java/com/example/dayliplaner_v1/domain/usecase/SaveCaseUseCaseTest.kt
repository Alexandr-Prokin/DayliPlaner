package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.models.DateTime
import com.example.dayliplaner_v1.domain.repository.AppRepository
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class SaveCaseUseCaseTest {

    private val appRepository = mock<AppRepository>()

    @Test
    fun execute() {
        val testCaseRecord = CaseRecordModel(
            name = "name",
            description = "description",
            dateStart = DateTime(1, 2, 2022, 1, 1),
            dateFinish = DateTime(1, 2, 2022, 2, 1)
        )

        Mockito.`when`(appRepository.saveCaseRecord(caseRecordModel = testCaseRecord)).thenReturn(true)
        val useCase = SaveCaseUseCase(appRepository = appRepository)
        val actual = useCase.execute(caseRecordModel = testCaseRecord)
        val expected = true

        assertEquals(expected, actual)
    }
}
