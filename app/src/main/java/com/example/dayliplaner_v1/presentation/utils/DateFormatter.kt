package com.example.dayliplaner_v1.presentation.utils

import com.example.dayliplaner_v1.domain.models.DateTime
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateFormatter {

    companion object {
        private const val TIME_TEMPLATE = "HH:mm"
        private const val DATE_TEMPLATE = "dd-MM-yyyy"
    }

    private val timeStandardFormatter = DateTimeFormatter.ofPattern(TIME_TEMPLATE, Locale.getDefault())

    fun getTimeStandard(dateTime: LocalTime): String {
        return dateTime.format(timeStandardFormatter)
    }

    fun getTimeStandard(timeStamp: Long): String {
        return SimpleDateFormat("$TIME_TEMPLATE").format(timeStamp * 1000L)
    }
    fun getDate(timeStamp: Long): String {

        val dayTimeStamp = SimpleDateFormat("dd").format(timeStamp.toInt() * 1000L).toInt()
        val monthTimeStamp = SimpleDateFormat("MM").format(timeStamp.toInt() * 1000L).toInt()
        val yearTimeStamp = SimpleDateFormat("yyyy").format(timeStamp.toInt() * 1000L).toInt()
        return "$dayTimeStamp-$monthTimeStamp-$yearTimeStamp"
    }

    fun getTimeStamp(startDate: DateTime): Long {
        val timeStamp = SimpleDateFormat("dd-MM-yyyy/HH:mm")
            .parse(
                with(startDate) {
                    "$day-$month-$year/$hours:$minutes"
                }
            )
        return timeStamp!!.time / 1000
    }
}