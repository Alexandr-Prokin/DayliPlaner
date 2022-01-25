package com.example.dayliplaner_v1.presentation.addcase

import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically

import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

class MyScreen()

@Composable
fun TimeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    error: String
) {
    Row() {
        Column() {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .size(66.dp)
                    .padding(top = 7.dp)
                    .border(0.dp, Color.Transparent)
                    .background(Color.White, CircleShape),
                shape = CircleShape,
                placeholder = { Text(placeHolder) },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                isError = error.isNotEmpty()
            )
            Text(
                error,
                Modifier
                    .padding(start = 16.dp),
                color = Color.Red,
                fontSize = 12.sp
            )
        }
        Column {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .size(66.dp)
                    .padding(top = 7.dp)
                    .border(0.dp, Color.Transparent)
                    .background(Color.White, CircleShape),
                shape = CircleShape,
                placeholder = { Text(placeHolder) },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                isError = error.isNotEmpty()
            )
            Text(
                error,
                Modifier
                    .padding(start = 16.dp),
                color = Color.Red,
                fontSize = 12.sp
            )
        }
    }
}
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: (String) -> Unit
) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .background(Color.LightGray)
            .size(130.dp, 50.dp),
    )
}


@Composable
fun PickerTime() {
    var hour by remember { mutableStateOf(0) }
    var minute by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(false) }
    Column() {
        Row() {
            OutlinedTextField(
            value = "${hour}:${minute}", onValueChange = {},
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
        if(visible){
            Row() {
                AndroidView(
                    modifier = Modifier.wrapContentSize(),
                    factory = { context ->
                        NumberPicker(context).apply {
                            setOnValueChangedListener { numberPicker, i, i2 ->
                                hour = i2
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
                                minute = i2
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
fun PickerMinuts() {
    var minute by remember { mutableStateOf(0) }
    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = { context ->
            NumberPicker(context).apply {
                setOnValueChangedListener { numberPicker, i, i2 ->
                    minute = i2
                }
                minValue = 0
                maxValue = 59
            }
        }
    )
}

@Composable
fun TimePicker() {

    Row() {
        PickerTime()
        PickerMinuts()
    }
}
@Composable
fun TwoTimePicker() {
    Row() {
        TimePicker()
        Spacer(modifier = Modifier.size(10.dp))
        TimePicker()
    }
}



@Composable
@Preview(showSystemUi = true, name = "MyTime")
fun ShowMe() {
    PickerTime()
}

// fun ShowMe() {
//    var visible by remember { mutableStateOf(false) }
//    var textValue = remember { mutableStateOf(" ") }
//    //
//    val localFocusManager = LocalFocusManager.current
//
//    Column() {
//        TextField(
//            value = "",
//            onValueChange = { textValue.value = it },
//            modifier = Modifier.onFocusChanged { focusState ->
//                if (visible != focusState.isFocused) {
//                    visible = true
//                }
//                if (!focusState.isFocused) {
//                    visible = false
//                }
//            },
//        )
//        if (visible) {
//            TimePicker()
//        }
//    }
// }
