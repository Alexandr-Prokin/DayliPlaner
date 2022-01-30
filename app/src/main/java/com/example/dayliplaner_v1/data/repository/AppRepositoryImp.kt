package com.example.dayliplaner_v1.data.repository

import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.repository.AppRepository
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase
import io.realm.Realm
import io.realm.RealmResults
import io.realm.exceptions.RealmException

class AppRepositoryImp() : AppRepository {
    private var convertTime = ConvertTimeStampUseCase()

    private var realm: Realm = Realm.getDefaultInstance()
    override fun getData(): RealmResults<CaseRecord> {

        realm.where(CaseRecord::class.java).findAll().let {
            return it
        }
    }
    override fun getOne(id: Int): CaseRecord? {

        realm.where(CaseRecord::class.java)
            .equalTo("id", id)
            .findFirst().let {
                return it
            }
    }
    override fun saveCaseRecord(caseRecordModel: CaseRecordModel): Boolean {
        realm.beginTransaction()
        var count = 0

        for (i in getData()) {
            count++
        }
        return try {
            val caseRecord = realm.createObject(CaseRecord::class.java)
            caseRecord.setId(count + 1)
            caseRecordModel.name?.let { caseRecord.setName(it) }
            caseRecordModel.description?.let { caseRecord.setDescription(it) }
            caseRecord.setDateStart(convertTime.getTimeStamp(caseRecordModel.dateStart))
            caseRecord.setDateFinish(convertTime.getTimeStamp(caseRecordModel.dateFinish))

            realm.commitTransaction()
            return true
        } catch (e: RealmException) {
            false
        }
    }
}
