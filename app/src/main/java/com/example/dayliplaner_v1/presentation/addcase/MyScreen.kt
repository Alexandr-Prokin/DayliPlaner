package com.example.dayliplaner_v1.presentation.addcase

import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

class MyScreen()

@Composable
fun PickerStartTime() {

    var startHour by remember { mutableStateOf(0) }
    var startMinute by remember { mutableStateOf(0) }
    addCaseViewModel.caseRecordModel.dateStart.hours = startHour
    addCaseViewModel.caseRecordModel.dateStart.minute = startMinute
    var visible by remember { mutableStateOf(false) }

    Column() {
        Row() {
            OutlinedTextField(
                value = "$startHour:$startMinute", onValueChange = {},
                textStyle = TextStyle(textAlign = TextAlign.Center),
                shape = CircleShape,
                modifier = Modifier
                    .border(0.dp, Color.Transparent)
                    .background(Color.Transparent)
                    .size(130.dp, 50.dp)
                    .onFocusChanged { focusState ->
                        if (visible != focusState.isFocused) {
                            visible = true
                        }
                        if (!focusState.isFocused) {
                            visible = false
                        }
                    }
            )
        }
        if (visible) {
            Row() {
                AndroidView(
                    modifier = Modifier.wrapContentSize(),
                    factory = { context ->
                        NumberPicker(context).apply {
                            setOnValueChangedListener { numberPicker, i, i2 ->
                                startHour = i2
                            }
                            minValue = 0
                            maxValue = 23
                        }
                    }
                )
                AndroidView(
                    modifier = Modifier.wrapContentSize(),
                    factory = { context ->
                        NumberPicker(context).apply {
                            setOnValueChangedListener { numberPicker, i, i2 ->
                                startMinute = i2
                            }
                            minValue = 0
                            maxValue = 59
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PickerFinishTime() {

    var finishHour by remember { mutableStateOf(0) }
    var finishMinute by remember { mutableStateOf(0) }
    addCaseViewModel.caseRecordModel.dateFinish.hours = finishHour
    addCaseViewModel.caseRecordModel.dateFinish.minute = finishHour
    var visible by remember { mutableStateOf(false) }

    Column() {
        Row() {
            OutlinedTextField(
                value = "$finishHour:$finishMinute", onValueChange = {},
                textStyle = TextStyle(textAlign = TextAlign.Center),
                shape = CircleShape,
                modifier = Modifier
                    .border(0.dp, Color.Transparent)
                    .background(Color.Transparent)
                    .size(130.dp, 50.dp)
                    .onFocusChanged { focusState ->
                        if (visible != focusState.isFocused) {
                            visible = true
                        }
                        if (!focusState.isFocused) {
                            visible = false
                        }
                    }
            )
        }
        if (visible) {
            Row() {
                AndroidView(
                    modifier = Modifier.wrapContentSize(),
                    factory = { context ->
                        NumberPicker(context).apply {
                            setOnValueChangedListener { numberPicker, i, i2 ->
                                finishHour = i2
                            }
                            minValue = 0
                            maxValue = 23
                        }
                    }
                )
                AndroidView(
                    modifier = Modifier.wrapContentSize(),
                    factory = { context ->
                        NumberPicker(context).apply {
                            setOnValueChangedListener { numberPicker, i, i2 ->
                                finishMinute = i2
                            }
                            minValue = 0
                            maxValue = 59
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, name = "MyTime")
fun ShowMe() {
//    PickerTime(_hour = { 1 } , _minute = { 2 } )
}
