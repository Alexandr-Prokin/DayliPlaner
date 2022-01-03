package com.example.dayliplaner_v1

import io.realm.RealmObject

open class CaseRecord : RealmObject() {

    private var id: Int = 0
    private var dateStart: String = ""
    private var dateFinish: String = ""
    private var name: String = ""
    private var description: String = ""

    fun setId(id: Int) {
        this.id = id
    }
    fun getId(): Int {
        return id
    }
    fun setDateStart(dateStart: String) {
        this.dateStart = dateStart
    }
    fun getDateStart(): String {
        return dateStart
    }
    fun setDateFinish(dateFinish: String) {
        this.dateFinish = dateFinish
    }
    fun getDateFinish(): String {
        return dateFinish
    }
    fun setName(name: String) {
        this.name = name
    }
    fun getName(): String {
        return name
    }
    fun setDescription(description: String) {
        this.description = description
    }
    fun getDescription(): String {
        return description
    }
}
