package com.example.dayliplaner_v1.presentation.addcase

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.models.DateTime
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepositoryImpl
import com.example.dayliplaner_v1.domain.usecase.SaveCaseUseCase
import com.example.dayliplaner_v1.presentation.utils.DateFormatter

class AddCaseViewModel : ViewModel() {

    private val appRepository = CaseRecordRepositoryImpl()
    private val saveCaseUseCase = SaveCaseUseCase(appRepository)
    private val dateFormatter = DateFormatter()
    val start: DateTime = DateTime(0, 0, 0, 0, 0)
    val finish: DateTime = DateTime(0, 0, 0, 0, 0)

    var errorDay = false
    val caseRecordModel: CaseRecordModel = CaseRecordModel(
        dateStart = 0,
        dateFinish = 0,
        name = "",
        description = ""
    )

    fun setDay(year: Int, month: Int, dayOfMonth: Int) {
        start.year = year
        start.day = dayOfMonth
        start.month = month
        finish.year = year
        finish.day = dayOfMonth
        finish.month = month
    }

    fun saveCase(): Boolean {
        caseRecordModel.dateStart = dateFormatter.getTimeStamp(start)
        caseRecordModel.dateFinish = dateFormatter.getTimeStamp(finish)
        return if (caseRecordModel.dateStart > 0 &&
            caseRecordModel.name != "" &&
            caseRecordModel.description != ""
        ) {
            saveCaseUseCase.execute(caseRecordModel)
        } else {
            errorDay = caseRecordModel.dateStart <= 0
            false
        }
    }
}
