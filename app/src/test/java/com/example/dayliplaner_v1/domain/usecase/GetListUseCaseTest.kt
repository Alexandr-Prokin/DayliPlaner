package com.example.dayliplaner_v1.domain.usecase


import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.repository.AppRepository
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetListUseCaseTest {

    private val appRepository = mock<AppRepository>()

    @Test
    fun getCaseRecord() {
        val testId = 1
        val testCaseRecord : CaseRecord? = null

        Mockito.`when`(appRepository.getCaseRecord(id = testId)).thenReturn(testCaseRecord)
        val useCase =  GetListUseCase(appRepository = appRepository)
        val actual =  useCase.getCaseRecord(id = testId)

        Assert.assertEquals(testCaseRecord, actual)
    }
}