package com.example.dayliplaner_v1.presentation.addcase

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.repository.AppRepositoryImp
import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.models.DateTime
import com.example.dayliplaner_v1.domain.usecase.SaveCaseUseCase

class AddCaseViewModel : ViewModel() {
    private val appRepository = AppRepositoryImp()
    private val saveCaseUseCase = SaveCaseUseCase(appRepository)
    var errorDay = false
    var errorName = false
    var errorDescription = false
    var errorNameText = ""
    var errorDescriptionText = ""
    private val errorText = "Is not empty"
    val caseRecordModel: CaseRecordModel = CaseRecordModel(
        DateTime(null, null, null, null, null),
        DateTime(null, null, null, null, null),
        null,
        null
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
        return if (caseRecordModel.dateStart.day != null &&
            caseRecordModel.name != "" &&
            caseRecordModel.description != ""
        ) {
            saveCaseUseCase.execute(caseRecordModel)
        } else {
            errorDay = caseRecordModel.dateStart.day == null
            false
        }
    }
}
