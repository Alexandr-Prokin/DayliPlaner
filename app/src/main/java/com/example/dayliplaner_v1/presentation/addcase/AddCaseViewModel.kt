package com.example.dayliplaner_v1.presentation.addcase

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.dayliplaner_v1.data.DataApp
import com.example.dayliplaner_v1.domain.models.DateTime

class AddCaseViewModel : ViewModel() {
    var dataApp: DataApp = DataApp()
    var dateTimeStart: DateTime = DateTime(null, null, null, null, 0)
    var dateTimeFinish: DateTime = DateTime(null, null, null, null, 0)
    var name: String = ""
    var description: String = ""
    var backFragment: Boolean = false

    fun setDay(year: Int, month: Int, dayOfMonth: Int) {
        dateTimeStart.year = year
        dateTimeStart.month = month
        dateTimeStart.day = dayOfMonth
        dateTimeFinish.year = year
        dateTimeFinish.month = month
        dateTimeFinish.day = dayOfMonth

        // Log.e("Tag", "setDay=${dayMonthYear.year}/${dayMonthYear.month}/${dayMonthYear.dayOfMonth}")
    }

    fun saveCase(context: Context) {
        if (dateTimeStart.day != null) {
            dataApp.saveCaseRecord(name, description, dateTimeStart, dateTimeFinish)

            // Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()

            backFragment = true //
        } else {
            Toast.makeText(context, "Выберите дату", Toast.LENGTH_SHORT).show()
        }
    }
}
