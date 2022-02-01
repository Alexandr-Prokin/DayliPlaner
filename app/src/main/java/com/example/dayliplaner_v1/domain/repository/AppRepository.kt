package com.example.dayliplaner_v1.domain.repository

import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import io.realm.RealmResults
// TODO: переименовать
interface AppRepository {

    fun getListCaseRecord(): RealmResults<CaseRecord>

    fun getCaseRecord(id: Int): CaseRecord?

    fun saveCaseRecord(caseRecordModel: CaseRecordModel): Boolean
}
