package com.example.dayliplaner_v1.presentation.calendar

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.data.repository.AppRepositoryImp
import com.example.dayliplaner_v1.domain.usecase.GetDataUseCase
import io.realm.RealmResults

class CalendarViewModel : ViewModel() {
    private val appRepository = AppRepositoryImp()
    private val getDataUseCase = GetDataUseCase(appRepository)

    fun getData(): RealmResults<CaseRecord> {
        return getDataUseCase.get()
    }
}
