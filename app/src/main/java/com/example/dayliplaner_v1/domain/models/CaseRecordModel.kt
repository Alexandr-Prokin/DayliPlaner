package com.example.dayliplaner_v1.domain.models

data class CaseRecordModel(

    var dateStart: DateTime,
    var dateFinish: DateTime,
    var name: String?,
    var description: String?
)
