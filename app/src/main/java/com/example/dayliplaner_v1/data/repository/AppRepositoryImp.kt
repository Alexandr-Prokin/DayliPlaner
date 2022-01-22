package com.example.dayliplaner_v1.data.repository

import com.example.dayliplaner_v1.data.CaseRecord
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
            var caseRecord = realm.createObject(CaseRecord::class.java)
            caseRecord.setId(count + 1)
            caseRecord.setName(caseRecordModel.name)
            caseRecord.setDescription(caseRecordModel.description)
            caseRecord.setDateStart(convertTime.setTimeStamp(caseRecordModel.dateStart))
            caseRecord.setDateFinish(convertTime.setTimeStamp(caseRecordModel.dateFinish))

            realm.commitTransaction()
            return true
        } catch (e: RealmException) {
            // Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            false
        }
    }
}
