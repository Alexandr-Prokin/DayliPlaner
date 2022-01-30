package com.example.dayliplaner_v1.domain.usecase

import java.text.SimpleDateFormat

class GetTimeUseCase {

    fun getTime(timeStamp: String): String {
        val dayTimeHoursStamp = SimpleDateFormat("HH").format(timeStamp.toInt() * 1000L).toString()
        val dayTimeMinutesStamp = SimpleDateFormat("mm").format(timeStamp.toInt() * 1000L).toString()
        return "$dayTimeHoursStamp:$dayTimeMinutesStamp"
    }
}
