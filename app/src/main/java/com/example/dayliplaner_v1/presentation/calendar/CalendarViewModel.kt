package com.example.dayliplaner_v1.presentation.calendar

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepositoryImpl
import com.example.dayliplaner_v1.domain.usecase.GetDataUseCase
import io.realm.RealmResults

class CalendarViewModel : ViewModel() {
    private val appRepository = CaseRecordRepositoryImpl()
    private val getDataUseCase = GetDataUseCase(appRepository)

    fun getCaseRecord(): RealmResults<CaseRecord> {
        return getDataUseCase.execute()
    }
}
