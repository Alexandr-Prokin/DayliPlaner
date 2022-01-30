package com.example.dayliplaner_v1.domain.usecase

import com.example.dayliplaner_v1.domain.models.DateTime
import java.text.SimpleDateFormat
import java.util.*

class ConvertTimeStampUseCase {

    fun getTimeStamp(dateTime: DateTime): String {

        val timeStamp = SimpleDateFormat("dd-MM-yyyy/hh:mm")
            .parse(
                "${dateTime.day}-${dateTime.month}-${dateTime.year}/${dateTime.hours}:${dateTime.minute}"
            )
        return (timeStamp.time / 1000).toString()
    }
}
