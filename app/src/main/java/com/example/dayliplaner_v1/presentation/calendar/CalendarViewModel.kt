package com.example.dayliplaner_v1.presentation.calendar

import androidx.lifecycle.ViewModel
import io.realm.Realm

class CalendarViewModel : ViewModel() {
    var realm: Realm = Realm.getDefaultInstance()
}
