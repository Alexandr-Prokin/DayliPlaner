package com.example.dayliplaner_v1.presentation.addcase

import android.os.Bundle
import android.widget.CalendarView
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController
import com.example.dayliplaner_v1.presentation.addcase.theme.DayliPlaner_v1Theme

class AddCaseScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DayliPlaner_v1Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    var addCaseViewModel = AddCaseViewModel()
    val navController = rememberNavController()
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val errorName = remember { mutableStateOf("") }
    val errorDescription = remember { mutableStateOf("") }

    addCaseViewModel.caseRecordModel.name = name.value
    addCaseViewModel.caseRecordModel.description = description.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(2), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.dp, Color.Transparent)
                    .background(color = Color.Transparent),

                onClick = {
                    navController
                        .navigate(
                            "calendarFragment"
                        )
                }
            ) {
                Text(text = "<- BACK", color = Color.Black)
            }
        }

        MyCalendarView(addCaseViewModel)

        AppTextField(
            value = name.value,
            onValueChange = { name.value = it },
            placeHolder = "Enter name",
            error = errorName.value
        )
        AppTextField(
            value = description.value,
            onValueChange = { description.value = it },
            placeHolder = "Enter description",
            error = errorDescription.value
        )
        Row {
            // start time
            PickerStartTime(addCaseViewModel)
            Spacer(modifier = Modifier.size(10.dp))
            // end time
            PickerFinishTime(addCaseViewModel)
        }
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedButton(
            modifier = Modifier
                .padding(6.dp),
            shape = CircleShape,
            onClick = {
                // свернуть дата пикер

                if (addCaseViewModel.saveCase()) {
                    navController.navigate("calendarFragment")
                } else {
                    if (addCaseViewModel.errorDay) {
                        Toast.makeText(context, "select a date", Toast.LENGTH_SHORT).show()
                    }
                    if (name.value.isEmpty()) {
                        errorName.value = "is not empty"
                    } else {
                        errorName.value = ""
                    }
                    if (description.value.isEmpty()) {
                        errorDescription.value = "is not empty"
                    } else {
                        errorDescription.value = ""
                    }
                }
            }

        ) {
            Text(text = "SAVE")
        }
    }
}
@Composable
fun MyCalendarView(addCaseViewModel: AddCaseViewModel) {
    AndroidView(
        {
            CalendarView(it)
        },
        Modifier.fillMaxWidth(),
        update = { view ->
            view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                addCaseViewModel.setDay(year, month + 1, dayOfMonth)
            }
        }
    )
}
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    error: String
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
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
@Composable
fun PickerStartTime(addCaseViewModel: AddCaseViewModel) {

    var startHour by remember { mutableStateOf(0) }
    var startMinute by remember { mutableStateOf(0) }
    addCaseViewModel.caseRecordModel.dateStart.hours = startHour
    addCaseViewModel.caseRecordModel.dateStart.minute = startMinute
    var visible by remember { mutableStateOf(false) }

    Column {
        Row {
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
            Row {
                AndroidView(
                    modifier = Modifier.wrapContentSize(),
                    factory = { context ->
                        NumberPicker(context).apply {
                            setOnValueChangedListener { _, _, i2 ->
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
                            setOnValueChangedListener { _, _, i2 ->
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
fun PickerFinishTime(addCaseViewModel: AddCaseViewModel) {

    var finishHour by remember { mutableStateOf(0) }
    var finishMinute by remember { mutableStateOf(0) }
    addCaseViewModel.caseRecordModel.dateFinish.hours = finishHour
    addCaseViewModel.caseRecordModel.dateFinish.minute = finishHour
    var visible by remember { mutableStateOf(false) }

    Column {
        Row {
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
            Row {
                AndroidView(
                    modifier = Modifier.wrapContentSize(),
                    factory = { context ->
                        NumberPicker(context).apply {
                            setOnValueChangedListener { _, _, i2 ->
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
                            setOnValueChangedListener { _, _, i2 ->
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
//
// @Preview(showSystemUi = true, name = "AddCaseScreen")
// @Composable
// fun DefaultPreview() {
//
//    MainScreen()
// }
