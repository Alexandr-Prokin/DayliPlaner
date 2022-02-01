package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.repository.AppRepository
import io.realm.RealmResults

class GetDataUseCase(private val appRepository: AppRepository) {
    fun get(): RealmResults<CaseRecord> {
        return appRepository.getListCaseRecord()
    }
}
