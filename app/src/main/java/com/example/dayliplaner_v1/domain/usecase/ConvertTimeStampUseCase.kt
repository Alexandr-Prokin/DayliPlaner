package com.example.dayliplaner_v1.domain.usecase

import java.text.SimpleDateFormat


class ConvertTimeStampUseCase {

    fun setTimeStamp(dd: Int, MM: Int, yyyy: Int, hh: Int, mm: Int): String {
        return SimpleDateFormat("dd-MM-yyyy hh:mm").parse("$dd-$MM-$yyyy $hh:$mm").toString()
    }
}
