package com.example.dayliplaner_v1.presentation.addcase

import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
) {
//    OutlinedTextField(
//        value = value, onValueChange = onValueChange,
//        modifier = Modifier
//
//            .border(1.dp, Color.Black)
//            .background(Color.LightGray)
//            .size(50.dp, 50.dp),)
}

@Composable
fun TwoTime(
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Start time")
        OneTime(value = value, onValueChange = onValueChange)
        Text(text = "End time")
        OneTime(value = value, onValueChange = onValueChange)
    }
}
@Composable
fun PickerHours() {
    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = { context ->
            NumberPicker(context).apply {
                setOnValueChangedListener { numberPicker, i, i2 -> }
                minValue = 0
                maxValue = 23
            }
        }
    )
}
@Composable
fun PickerMinets() {
    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = { context ->
            NumberPicker(context).apply {
                setOnValueChangedListener { numberPicker, i, i2 -> }
                minValue = 0
                maxValue = 59
            }
        }
    )
}
@Composable
fun TimePicker() {
    Row() {
        PickerHours()
        PickerMinets()
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
fun OneTime(
    value: String,
    onValueChange: (String) -> Unit,
) {
    Row() {
        AppTextField(value = value, onValueChange = onValueChange)
        Text(text = ":")
        AppTextField(value = value, onValueChange = onValueChange)
    }
}

@Composable
@Preview(showSystemUi = true, name = "MyTime")
fun ShowMe() {
    TwoTimePicker()
}
