package com.example.dayliplaner_v1.presentation.casedescription

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.data.repository.AppRepositoryImp
import com.example.dayliplaner_v1.domain.models.CaseDescription
import com.example.dayliplaner_v1.domain.usecase.GetListUseCase
import com.example.dayliplaner_v1.domain.usecase.GetTimeUseCase

class CaseDescriptionViewModel : ViewModel() {

    var caseDescription = CaseDescription(null, null, null)
    private val appRepository = AppRepositoryImp()
    private val getListUseCase = GetListUseCase(appRepository)
    private var getTime = GetTimeUseCase()

    fun getList(id: Int) {
        viewGetDB(getListUseCase.getList(id))
    }
    private fun viewGetDB(caseRecord: CaseRecord?) {

        val timeStart: String = getTime.getTime(caseRecord!!.getDateStart())
        val timeFinish: String = getTime.getTime(caseRecord.getDateFinish())
        caseDescription.name = caseRecord.getName()
        caseDescription.description = caseRecord.getDescription()
        caseDescription.time = "$timeStart-$timeFinish"
    }
}
