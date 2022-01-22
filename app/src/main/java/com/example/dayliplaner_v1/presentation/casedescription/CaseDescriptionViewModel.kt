package com.example.dayliplaner_v1.presentation.casedescription

import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.CaseRecord
import com.example.dayliplaner_v1.data.repository.AppRepositoryImp
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase
import io.realm.Realm
import kotlin.properties.Delegates

class CaseDescriptionViewModel : ViewModel() {
    // в один объект
    var name: String? = null
    var description: String? = null
    var time: String? = null
    var id by Delegates.notNull<Int>()
    //
    val appRepositoryImp: AppRepositoryImp = AppRepositoryImp()
    var realm: Realm = Realm.getDefaultInstance()
    private var convertTime = ConvertTimeStampUseCase()

    fun getList(id: Int) {
        viewGetDB(appRepositoryImp.getOne(id))
    }
    private fun viewGetDB(caseRecord: CaseRecord?) {

        var timeStart: String = convertTime.getTime(caseRecord!!.getDateStart())
        var timeFinish: String = convertTime.getTime(caseRecord.getDateFinish())
        name = caseRecord.getName()
        description = caseRecord.getDescription()
        time = "$timeStart-$timeFinish"
    }
}
