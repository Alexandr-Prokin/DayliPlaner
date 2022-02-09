package com.example.dayliplaner_v1.presentation.addcase

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepositoryImpl
import com.example.dayliplaner_v1.domain.usecase.SaveCaseUseCase
import java.time.LocalDateTime

class AddCaseViewModel : ViewModel() {

    private val appRepository = CaseRecordRepositoryImpl()
    private val saveCaseUseCase = SaveCaseUseCase(appRepository)

    var errorDay = false
    val caseRecordModel: CaseRecordModel = CaseRecordModel(
        dateStart = LocalDateTime.of(1,1,1,0,0),
        dateFinish = LocalDateTime.of(1,1,1,0,0),
        name = "",
        description = ""
    )

    fun saveCase(): Boolean {
        return if (
            caseRecordModel.dateStart != LocalDateTime.of(1,1,1,0,0) &&
            caseRecordModel.name != "" &&
            caseRecordModel.description != ""
        ) {
            saveCaseUseCase.execute(caseRecordModel)
        } else {
            errorDay = caseRecordModel.dateStart == LocalDateTime.of(1,1,1,0,0)
            false
        }
    }
}
