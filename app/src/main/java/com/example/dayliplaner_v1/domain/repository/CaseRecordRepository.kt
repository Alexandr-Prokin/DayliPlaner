package com.example.dayliplaner_v1.domain.repository

import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import io.realm.RealmResults

interface CaseRecordRepository {

    fun getListCaseRecord(): RealmResults<CaseRecord>

    fun getCaseRecord(id: Int): CaseRecord?

    fun saveCaseRecord(caseRecordModel: CaseRecordModel): Boolean
}
