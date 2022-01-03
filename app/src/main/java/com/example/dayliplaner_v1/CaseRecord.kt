package com.example.dayliplaner_v1

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CaseRecord(
    @PrimaryKey
    val id: Int,
    val dateStart: Int,
    val dateFinish: Int,
    val name: String,
    val description: String
) : RealmObject()
