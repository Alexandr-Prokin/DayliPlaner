package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepository

class GetListUseCase(private val caseRecordRepository: CaseRecordRepository) {
    fun getCaseRecord(id: Int): CaseRecord? {
        return caseRecordRepository.getCaseRecord(id)
    }
}
