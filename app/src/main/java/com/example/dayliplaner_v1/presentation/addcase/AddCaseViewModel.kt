package com.example.dayliplaner_v1.presentation.addcase

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.repository.AppRepositoryImp
import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.models.DateTime
import com.example.dayliplaner_v1.domain.usecase.SaveCaseUseCase

class AddCaseViewModel : ViewModel() {
    private val appRepository = AppRepositoryImp()
    private val saveCaseUseCase = SaveCaseUseCase(appRepository)

    val caseRecordModel: CaseRecordModel = CaseRecordModel(
        DateTime(null, null, null, 11, 0),
        DateTime(null, null, null, 13, 0),
        "",
        ""
    )

    fun setDay(year: Int, month: Int, dayOfMonth: Int) {
        caseRecordModel.dateStart.year = year
        caseRecordModel.dateStart.month = month
        caseRecordModel.dateStart.day = dayOfMonth
        caseRecordModel.dateFinish.year = year
        caseRecordModel.dateFinish.month = month
        caseRecordModel.dateFinish.day = dayOfMonth
    }

    fun saveCase(): Boolean {
        return if (caseRecordModel.dateStart.day != null) {
            saveCaseUseCase.execute(caseRecordModel)
        } else {
            false
        }
    }
}
