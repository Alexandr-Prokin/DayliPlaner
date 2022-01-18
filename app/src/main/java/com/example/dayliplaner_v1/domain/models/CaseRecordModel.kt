package com.example.dayliplaner_v1.domain.models

data class CaseRecordModel(
    var id: Int,
    var dateStart: String,
    var dateFinish: String,
    var name: String,
    var description: String
)
