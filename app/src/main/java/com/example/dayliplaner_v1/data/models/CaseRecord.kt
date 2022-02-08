package com.example.dayliplaner_v1.data.models

import io.realm.RealmObject

open class CaseRecord : RealmObject() {

    private var id: Int = 0
    private var dateStart: Long = 0
    private var dateFinish: Long = 0
    private var name: String = ""
    private var description: String = ""

    fun setId(id: Int) {
        this.id = id
    }
    fun getId(): Int {
        return id
    }
    fun setDateStart(dateStart: Long) {
        this.dateStart = dateStart
    }
    fun getDateStart(): Long {
        return dateStart
    }
    fun setDateFinish(dateFinish: Long) {
        this.dateFinish = dateFinish
    }
    fun getDateFinish(): Long {
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
