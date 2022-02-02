package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepository

class SaveCaseUseCase(private val caseRecordRepository: CaseRecordRepository) {
    fun execute(caseRecordModel: CaseRecordModel): Boolean {
        return caseRecordRepository.saveCaseRecord(caseRecordModel)
    }
}
