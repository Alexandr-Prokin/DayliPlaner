package com.example.dayliplaner_v1.presentation.addcase

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.domain.models.CaseRecordModel
import com.example.dayliplaner_v1.domain.models.DateTime
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepositoryImpl
import com.example.dayliplaner_v1.domain.usecase.SaveCaseUseCase
import com.example.dayliplaner_v1.presentation.utils.DateFormatter
import java.time.LocalDateTime

class AddCaseViewModel : ViewModel() {

    private val appRepository = CaseRecordRepositoryImpl()
    private val saveCaseUseCase = SaveCaseUseCase(appRepository)
    private val dateFormatter = DateFormatter()

    var errorDay = false
    val caseRecordModel: CaseRecordModel = CaseRecordModel(
        dateStart = LocalDateTime.of(1,1,1,0,0),
        dateFinish = LocalDateTime.of(1,1,1,0,0),
        name = "",
        description = ""
    )

    fun setDay(year: Int, month: Int, dayOfMonth: Int) {

    }

    fun saveCase(): Boolean {
//        caseRecordModel.dateStart =
//        caseRecordModel.dateFinish = dateFormatter.getTimeStamp(finish)
        return if (
            //caseRecordModel.dateStart > 0 &&
            caseRecordModel.name != "" &&
            caseRecordModel.description != ""
        ) {
            saveCaseUseCase.execute(caseRecordModel)
        } else {
            //errorDay = caseRecordModel.dateStart <= 0
            false
        }
    }
}
