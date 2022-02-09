package com.example.dayliplaner_v1.domain.models

import java.time.LocalDateTime

data class CaseRecordModel(

    var dateStart: LocalDateTime,
    var dateFinish: LocalDateTime,
    var name: String,
    var description: String
)
