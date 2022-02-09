package com.example.dayliplaner_v1.presentation.addcase

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepositoryImpl
import com.example.dayliplaner_v1.domain.usecase.SaveCaseUseCase

class AddCaseViewModel : ViewModel() {

    private val appRepository = CaseRecordRepositoryImpl()
    private val saveCaseUseCase = SaveCaseUseCase(appRepository)

    var errorDay = false
    val caseRecordModel: CaseRecordModel = CaseRecordModel(
        dateStart = null,
        dateFinish = null,
        name = "",
        description = ""
    )

    fun saveCase(): Boolean {
        return if (
            caseRecordModel.dateStart != null &&
            caseRecordModel.name != "" &&
            caseRecordModel.description != ""
        ) {
            saveCaseUseCase.execute(caseRecordModel)
        } else {
            errorDay = caseRecordModel.dateStart == null
            false
        }
    }
}
