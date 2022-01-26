package com.example.dayliplaner_v1.domain.usecase

import android.util.Log
import com.example.dayliplaner_v1.domain.models.DateTime
import java.text.SimpleDateFormat
import java.util.*

class ConvertTimeStampUseCase {

    fun setTimeStamp(dateTime: DateTime): String {

        val timeStamp = SimpleDateFormat("dd-MM-yyyy/hh:mm").parse(
            "${dateTime.day}-${dateTime.month}-${dateTime.year}/${dateTime.hours}:${dateTime.minute}"
        )
        Log.e("Tag", "setTimeStamp=" + timeStamp.time / 1000)
        return (timeStamp.time / 1000).toString()
    }

    fun getDayMonthYear(timeStamp: String, year: Int, month: Int, day: Int): Boolean {

        val dayTimeStamp = SimpleDateFormat("dd").format(timeStamp.toInt() * 1000L).toInt()
        val monthTimeStamp = SimpleDateFormat("MM").format(timeStamp.toInt() * 1000L).toInt()
        val year = SimpleDateFormat("yyyy").format(timeStamp.toInt() * 1000L).toInt()
        Log.e("Tag", "_timeStamp=$dayTimeStamp-$monthTimeStamp-$year, $day-$month-$year")
        return "$dayTimeStamp-$monthTimeStamp-$year" == "$day-$month-$year"
    }
    fun getTime(timeStamp: String): String {

        val dayTimeHoursStamp = SimpleDateFormat("HH").format(timeStamp.toInt() * 1000L).toString()
        val dayTimeMinutesStamp = SimpleDateFormat("mm").format(timeStamp.toInt() * 1000L).toString()

        Log.e("Tag", "getTime=$dayTimeHoursStamp:$dayTimeMinutesStamp")
        return "$dayTimeHoursStamp:$dayTimeMinutesStamp"
    }
}
