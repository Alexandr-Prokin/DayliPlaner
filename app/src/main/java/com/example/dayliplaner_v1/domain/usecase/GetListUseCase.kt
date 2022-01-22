package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.repository.AppRepository

class GetListUseCase(private val appRepository: AppRepository) {

    fun getList(id: Int): CaseRecord? {
        return appRepository.getOne(id)
    }
}
