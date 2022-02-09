package com.example.dayliplaner_v1.presentation.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


class DateFormatter {

    companion object {
        private const val TIME_TEMPLATE = "HH:mm"
    }

    fun getTimeStandard(timeStamp: Long): String {
        return SimpleDateFormat("$TIME_TEMPLATE").format(timeStamp * 1000L)
    }
    fun convertLongToLocalDate(timeStamp: Long): LocalDate {
        return Instant.ofEpochSecond(timeStamp).atZone(ZoneId.systemDefault()).toLocalDate()
    }
}