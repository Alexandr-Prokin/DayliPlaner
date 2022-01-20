package com.example.dayliplaner_v1.presentation.calendar

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.CaseRecord
import com.example.dayliplaner_v1.data.DataApp
import io.realm.RealmResults

class CalendarViewModel : ViewModel() {
    private var dataApp: DataApp = DataApp()

    fun getData(): RealmResults<CaseRecord> {
        return dataApp.getData()
    }
}
