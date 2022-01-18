package com.example.dayliplaner_v1.presentation.addcase

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.CaseRecord
import com.example.dayliplaner_v1.domain.models.DateTime
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase
import io.realm.Realm
import io.realm.exceptions.RealmException

class AddCaseViewModel : ViewModel() {
    var realm: Realm = Realm.getDefaultInstance()
    private var convertTime = ConvertTimeStampUseCase()
    var dateTimeStart: DateTime = DateTime(null, null, null, null, 0)
    var dateTimeFinish: DateTime = DateTime(null, null, null, null, 0)
    var name: String = ""
    var description: String = ""

    fun setDay(year: Int, month: Int, dayOfMonth: Int) {
        dateTimeStart.year = year
        dateTimeStart.month = month
        dateTimeStart.day = dayOfMonth
        dateTimeFinish.year = year
        dateTimeFinish.month = month
        dateTimeFinish.day = dayOfMonth

        // Log.e("Tag", "setDay=${dayMonthYear.year}/${dayMonthYear.month}/${dayMonthYear.dayOfMonth}")
    }

    fun saveCase() {
        if (dateTimeStart.day != null) {
            realm.beginTransaction()
            var count = 0
            realm.where(CaseRecord::class.java).findAll().let {
                for (i in it) {
                    count++
                }
            }
            try {
                var caseRecord = realm.createObject(CaseRecord::class.java)
//                dateTimeStart.hours = binding.timeHourStartAddSpinner.selectedItem.toString()
//                dateTimeFinish.hours = binding.timeHourFinishAddSpinner.selectedItem.toString()
                caseRecord.setId(count + 1)
                caseRecord.setName(name)
                caseRecord.setDescription(description)
                caseRecord.setDateStart(convertTime.setTimeStamp(dateTimeStart))
                caseRecord.setDateFinish(convertTime.setTimeStamp(dateTimeFinish))

                realm.commitTransaction()
            } catch (e: RealmException) {
                // Toast.makeText(@activity, e.message, Toast.LENGTH_SHORT).show()
            }
            // this.findNavController().popBackStack()
        } else {
            // Toast.makeText(activity, "Выберите дату", Toast.LENGTH_SHORT).show()
        }
    }
}
