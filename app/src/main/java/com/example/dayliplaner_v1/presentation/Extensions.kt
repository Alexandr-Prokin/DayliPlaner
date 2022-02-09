package com.example.dayliplaner_v1.presentation

import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.convertToSecond() = atZone(ZoneId.systemDefault()).toInstant().epochSecond
