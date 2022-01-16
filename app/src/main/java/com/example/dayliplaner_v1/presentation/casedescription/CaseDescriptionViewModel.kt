package com.example.dayliplaner_v1.presentation.casedescription

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.CaseRecord
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase
import io.realm.Realm
import kotlin.properties.Delegates

class CaseDescriptionViewModel : ViewModel() {
    var name: String? = null
    var description: String? = null
    var time: String? = null
    var id by Delegates.notNull<Int>()
    var realm: Realm = Realm.getDefaultInstance()
    private var convertTime = ConvertTimeStampUseCase()

    init {
    }
    fun getList(id: Int) {
        realm.where(CaseRecord::class.java)
            .equalTo("id", id)
            .findAll().let {
                viewGetDB(it)
            }
        Log.e("Tag", "viewID$id")
    }
    private fun viewGetDB(caseRecord: List<CaseRecord>) {
        lateinit var timeStart: String
        lateinit var timeFinish: String
        for (i in caseRecord) {
            timeStart = convertTime.getTime(i.getDateStart())
            timeFinish = convertTime.getTime(i.getDateFinish())
            name = i.getName()
            description = i.getDescription()
            time = "$timeStart-$timeFinish"
        }
    }

    fun setClickButton() {
        TODO()
    }
}
