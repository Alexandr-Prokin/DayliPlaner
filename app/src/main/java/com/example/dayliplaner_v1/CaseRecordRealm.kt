package com.example.dayliplaner_v1

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CaseRecordRealm : RealmObject {
    @PrimaryKey var id: Int = 0
    var dateStart: Int = 0
    var dateFinish: Int = 0
    var name: String? = null
    var description: String? = null

    constructor(
        id: Int,
        dateStart: Int,
        dateFinish: Int,
        name: String,
        description: String
    ) {
        this.id = id
        this.dateStart = dateStart
        this.dateFinish = dateFinish
        this.name = name
        this.description = description
    }
    constructor()
}
