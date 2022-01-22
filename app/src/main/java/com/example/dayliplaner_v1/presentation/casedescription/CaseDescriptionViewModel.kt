package com.example.dayliplaner_v1.presentation.casedescription

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.data.repository.AppRepositoryImp
import com.example.dayliplaner_v1.domain.models.CaseDescription
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase
import com.example.dayliplaner_v1.domain.usecase.GetListUseCase

class CaseDescriptionViewModel : ViewModel() {

    var caseDescription = CaseDescription(null, null, null)
    private val appRepository = AppRepositoryImp()
    private val getListUseCase = GetListUseCase(appRepository)
    private var convertTime = ConvertTimeStampUseCase()

    fun getList(id: Int) {
        viewGetDB(getListUseCase.getList(id))
    }
    private fun viewGetDB(caseRecord: CaseRecord?) {

        val timeStart: String = convertTime.getTime(caseRecord!!.getDateStart())
        val timeFinish: String = convertTime.getTime(caseRecord.getDateFinish())
        caseDescription.name = caseRecord.getName()
        caseDescription.description = caseRecord.getDescription()
        caseDescription.time = "$timeStart-$timeFinish"
    }
}
