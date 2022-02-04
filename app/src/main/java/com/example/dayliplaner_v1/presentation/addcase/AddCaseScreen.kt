package com.example.dayliplaner_v1.presentation.addcase

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController
import com.example.dayliplaner_v1.R
import com.example.dayliplaner_v1.presentation.MainActivity
import com.example.dayliplaner_v1.presentation.addcase.theme.DayliPlaner_v1Theme
import io.realm.Realm

class AddCaseScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Realm.init(this)
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

@Composable
fun MainScreen() {

    val ERROR_SELECT_A_DATE = stringResource(R.string.select_a_date)
    val ERROR_IS_NOT_EMPTY = stringResource(R.string.is_not_empty)
    val ERROR_EMPTY = ""
    val context = LocalContext.current
    val addCaseViewModel = AddCaseViewModel()
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
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            ) {
                Text(text = stringResource(R.string.back), color = Color.Black)
            }
        }

        MyCalendarView(addCaseViewModel)

        AppTextField(
            value = name.value,
            onValueChange = { name.value = it },
            placeHolder = stringResource(R.string.place_holder_enter_name),
            label = stringResource(R.string.place_holder_enter_name),
            error = errorName.value
        )
        AppTextField(
            value = description.value,
            onValueChange = { description.value = it },
            placeHolder = stringResource(R.string.place_holder_enter_description),
            label = stringResource(R.string.place_holder_enter_description),
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
                if (addCaseViewModel.saveCase()) {
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                } else {
                    if (addCaseViewModel.errorDay) {
                        Toast.makeText(context, ERROR_SELECT_A_DATE, Toast.LENGTH_SHORT).show()
                    }
                    if (name.value.isEmpty()) {
                        errorName.value = ERROR_IS_NOT_EMPTY
                    } else {
                        errorName.value = ERROR_EMPTY
                    }
                    if (description.value.isEmpty()) {
                        errorDescription.value = ERROR_IS_NOT_EMPTY
                    } else {
                        errorDescription.value = ERROR_EMPTY
                    }
                }
            }

        ) {
            Text(text = stringResource(R.string.save_button))
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
    label: String,
    error: String
) {
    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .padding(top = 7.dp)
                .border(0.dp, Color.Transparent)
                .background(Color.White, CircleShape),
            shape = RoundedCornerShape(3.dp),
            placeholder = { Text(placeHolder) },
            label = { Text(label) },
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

    addCaseViewModel.start.hours = startHour
    addCaseViewModel.start.minutes = startMinute

    Column {
        Row(Modifier.padding(horizontal = 8.dp)) {
            Text(
                stringResource(R.string.start_time_add_case_screen),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 3.dp)
            )

            Text(
                text = "$startHour:$startMinute",
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 3.dp)
                    .background(Color.Transparent),
                textAlign = TextAlign.Center
            )
        }

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

@Composable
fun PickerFinishTime(addCaseViewModel: AddCaseViewModel) {

    var finishHour by remember { mutableStateOf(0) }
    var finishMinute by remember { mutableStateOf(0) }

    addCaseViewModel.finish.hours = finishHour
    addCaseViewModel.finish.minutes = finishHour

    Column {
        Row(Modifier.padding(horizontal = 8.dp)) {
            Text(
                stringResource(R.string.finish_time_add_case_screen), textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 3.dp)
            )
            Text(
                text = "$finishHour:$finishMinute",
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 3.dp)
                    .background(Color.Transparent),
                textAlign = TextAlign.Center
            )
        }

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
