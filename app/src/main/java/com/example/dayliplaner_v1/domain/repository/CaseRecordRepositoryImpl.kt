package com.example.dayliplaner_v1.domain.repository

import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import io.realm.Realm
import io.realm.RealmResults
import io.realm.exceptions.RealmException

class CaseRecordRepositoryImpl : CaseRecordRepository {

    private val realm: Realm = Realm.getDefaultInstance()

    override fun getListCaseRecord(): RealmResults<CaseRecord> {
        return realm.where(CaseRecord::class.java).findAll()
    }

    override fun getCaseRecord(id: Int): CaseRecord? {
        return realm.where(CaseRecord::class.java)
            .equalTo("id", id)
            .findFirst()
    }

    override fun saveCaseRecord(caseRecordModel: CaseRecordModel): Boolean = with(realm) {
        beginTransaction()
        return try {
            createObject(CaseRecord::class.java).apply {
                setId(getListCaseRecord().size)
                setName(caseRecordModel.name)
                setDescription(caseRecordModel.description)
                setDateStart(caseRecordModel.dateStart)
                setDateFinish(caseRecordModel.dateFinish)
            }
            commitTransaction()
            true
        } catch (exception: RealmException) {
            false
        }
    }
}
