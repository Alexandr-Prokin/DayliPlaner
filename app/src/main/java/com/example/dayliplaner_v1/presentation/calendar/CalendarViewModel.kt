package com.example.dayliplaner_v1.presentation.calendar

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.CaseRecord
import com.example.dayliplaner_v1.data.repository.AppRepositoryImp
import io.realm.RealmResults

class CalendarViewModel : ViewModel() {
    private var appRepositoryImp: AppRepositoryImp = AppRepositoryImp()

    fun getData(): RealmResults<CaseRecord> {
        return appRepositoryImp.getData()
    }
}
