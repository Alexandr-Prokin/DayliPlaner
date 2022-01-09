package com.example.dayliplaner_v1.domain.usecase

import android.util.Log
import com.example.dayliplaner_v1.domain.models.DateTime
import java.text.SimpleDateFormat
import java.util.*


class ConvertTimeStampUseCase {

    fun setTimeStamp(dateTime : DateTime): String {
        return SimpleDateFormat("dd-MM-yyyy hh:mm").parse("$dateTime").toString()
    }

    fun getDayMonthYear (timeStamp : String, year : Int, month: Int, day: Int): Boolean {
        val tS = 1641720388

        val dayTimeStamp = SimpleDateFormat("dd").format(tS*1000L).toInt()
        val monthTimeStamp = SimpleDateFormat("MM").format(tS*1000L).toInt()
        val Year = SimpleDateFormat("yyyy").format(tS*1000L).toInt()
        Log.e("Tag", "_timeStamp=$dayTimeStamp-$monthTimeStamp-$Year, $day-$month-$year")
        return "$dayTimeStamp-$monthTimeStamp-$Year" == "$day-$month-$year"
    }
}
