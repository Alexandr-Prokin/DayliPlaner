package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.repository.AppRepository

class GetListUseCase(private val appRepository: AppRepository) {

    fun getCaseRecord(id: Int): CaseRecord? {
        return appRepository.getCaseRecord(id)
    }
}
