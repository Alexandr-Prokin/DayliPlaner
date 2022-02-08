package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepository
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetListUseCaseTest {

    private val caseRecordRepository = mock<CaseRecordRepository>()

    @Test
    fun getCaseRecord() {
        val testId = 1
        val testCaseRecord : CaseRecord? = null

        Mockito.`when`(caseRecordRepository.getCaseRecord(id = testId)).thenReturn(testCaseRecord)
        val useCase =  GetListUseCase(caseRecordRepository = caseRecordRepository)
        val actual =  useCase.execute(id = testId)

        Assert.assertEquals(testCaseRecord, actual)
    }
}