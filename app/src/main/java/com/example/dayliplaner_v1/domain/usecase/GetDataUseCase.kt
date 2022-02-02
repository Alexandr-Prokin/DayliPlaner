package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepository
import io.realm.RealmResults

class GetDataUseCase(private val caseRecordRepository: CaseRecordRepository) {
    fun get(): RealmResults<CaseRecord> {
        return caseRecordRepository.getListCaseRecord()
    }
}
