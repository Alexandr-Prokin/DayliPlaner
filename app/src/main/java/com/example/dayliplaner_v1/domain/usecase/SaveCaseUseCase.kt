package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.repository.AppRepository

class SaveCaseUseCase(private val appRepository: AppRepository) {
    fun execute(caseRecordModel: CaseRecordModel): Boolean {
        return appRepository.saveCaseRecord(caseRecordModel)
    }
}
