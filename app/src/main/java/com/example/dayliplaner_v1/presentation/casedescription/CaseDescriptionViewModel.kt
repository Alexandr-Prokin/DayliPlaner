package com.example.dayliplaner_v1.presentation.casedescription

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.domain.models.CaseDescription
import com.example.dayliplaner_v1.domain.repository.CaseRecordRepositoryImpl
import com.example.dayliplaner_v1.domain.usecase.GetListUseCase
import com.example.dayliplaner_v1.presentation.utils.DateFormatter

class CaseDescriptionViewModel : ViewModel() {

    private val dateFormatter = DateFormatter()
    private val appRepository = CaseRecordRepositoryImpl()
    private val getListUseCase = GetListUseCase(appRepository)

    var caseDescription: CaseDescription = CaseDescription("", "", "")

    fun getList(id: Int) {

        val caseRecord = getListUseCase.execute(id)

        caseDescription = CaseDescription(
            description = caseRecord!!.getDescription(),
            name = caseRecord.getName(),
            time = dateFormatter.getTimeStandard(caseRecord.getDateStart()) + "-" +
                dateFormatter.getTimeStandard(caseRecord.getDateFinish())
        )
    }
}
