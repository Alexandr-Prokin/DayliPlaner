package com.example.dayliplaner_v1.data

import com.example.dayliplaner_v1.domain.models.DateTime
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase
import io.realm.Realm
import io.realm.RealmResults
import io.realm.exceptions.RealmException

class DataApp() {
    private var convertTime = ConvertTimeStampUseCase()
    private var realm: Realm = Realm.getDefaultInstance()
    fun getData(): RealmResults<CaseRecord> {

        realm.where(CaseRecord::class.java).findAll().let {
            return it
        }
    }
    fun getOne(id: Int): CaseRecord? {

        realm.where(CaseRecord::class.java)
            .equalTo("id", id)
            .findFirst().let { it ->
                return it
            }
    }
    fun saveCaseRecord(name: String, description: String, dateTimeStart: DateTime, dateTimeFinish: DateTime) {
        realm.beginTransaction()
        var count = 0

        for (i in getData()) {
            count++
        }
        try {
            var caseRecord = realm.createObject(CaseRecord::class.java)
            caseRecord.setId(count + 1)
            caseRecord.setName(name)
            caseRecord.setDescription(description)
            caseRecord.setDateStart(convertTime.setTimeStamp(dateTimeStart))
            caseRecord.setDateFinish(convertTime.setTimeStamp(dateTimeFinish))

            realm.commitTransaction()
        } catch (e: RealmException) {
            // Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
