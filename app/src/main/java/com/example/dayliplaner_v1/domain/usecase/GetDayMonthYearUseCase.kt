package com.example.dayliplaner_v1.domain.usecase

import java.text.SimpleDateFormat

class GetDayMonthYearUseCase {
    fun getDayMonthYear(timeStamp: String, year: Int, month: Int, day: Int): Boolean {
        val dayTimeStamp = SimpleDateFormat("dd").format(timeStamp.toInt() * 1000L).toInt()
        val monthTimeStamp = SimpleDateFormat("MM").format(timeStamp.toInt() * 1000L).toInt()
        val yearTimeStamp = SimpleDateFormat("yyyy").format(timeStamp.toInt() * 1000L).toInt()
        return "$dayTimeStamp-$monthTimeStamp-$yearTimeStamp" == "$day-$month-$year"
    }
}
