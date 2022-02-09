package com.example.dayliplaner_v1.presentation.addcase

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.dayliplaner_v1.R
import com.example.dayliplaner_v1.presentation.MainActivity
import com.example.dayliplaner_v1.presentation.addcase.theme.DayliPlaner_v1Theme
import com.example.dayliplaner_v1.presentation.convertToSecond
import io.realm.Realm
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

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

    val context = LocalContext.current
    val addCaseViewModel = AddCaseViewModel()
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val errorName = remember { mutableStateOf("") }
    val errorDescription = remember { mutableStateOf("") }
    val startHours = remember { mutableStateOf(0) }
    val startMinutes = remember { mutableStateOf(0) }
    val finishHours = remember { mutableStateOf(0) }
    val finishMinutes = remember { mutableStateOf(0) }
    val date = remember { mutableStateOf(LocalDate.of(1,1,1)) }

    addCaseViewModel.caseRecordModel.dateStart = LocalDateTime.of(date.value, LocalTime.of(startHours.value, startMinutes.value))
    addCaseViewModel.caseRecordModel.dateFinish = LocalDateTime.of(date.value, LocalTime.of(finishHours.value, finishMinutes.value))
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

        MyCalendarView(addCaseViewModel, date = {date.value = it})

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
            // start time TODO:
            PickerTime(
                nameText = stringResource(R.string.start_time_add_case_screen),
                onValueChangedHours = {startHours.value = it},
                onValueChangedMinutes = {startMinutes.value = it}
            )
            Spacer(modifier = Modifier.size(10.dp))
            // end time
            PickerTime(
                nameText = stringResource(R.string.finish_time_add_case_screen),
                onValueChangedHours = {finishHours.value = it},
                onValueChangedMinutes = {finishMinutes.value = it}
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedButton(
            modifier = Modifier
                .padding(6.dp),
            shape = CircleShape,
            onClick = {
                if (addCaseViewModel.saveCase()) {
                    Toast.makeText(context, R.string.saved, Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                } else {
                    if (addCaseViewModel.errorDay) {
                        Toast.makeText(context, R.string.select_a_date, Toast.LENGTH_SHORT).show()
                    }
                    if (name.value.isEmpty()) {
                        errorName.value =  R.string.is_not_empty.toString()
                    } else {
                        errorName.value = ""
                    }
                    if (description.value.isEmpty()) {
                        errorDescription.value = R.string.is_not_empty.toString()
                    } else {
                        errorDescription.value = ""
                    }
                }
            }

        ) {
            Text(text = stringResource(R.string.save_button))
        }
    }
}
@Composable
fun MyCalendarView(addCaseViewModel: AddCaseViewModel, date : (LocalDate) -> Unit) {
val context = LocalContext.current
    AndroidView(
        {
            CalendarView(it)
        },
        Modifier.fillMaxWidth(),
        update = { view ->
            view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                //addCaseViewModel.setDay(year, month + 1, dayOfMonth)
                //TODO:

                date.invoke(LocalDate.of(year, month+1, dayOfMonth))


//                var first = LocalDate.of(year, month+1 , dayOfMonth)
//
//                var second = LocalTime.of(1,2)   //(1970, 1, 1, 1,1)
//                val finishDate = LocalDateTime.of(first, second)
//                Log.e("Tag", "second=$second")
//                Log.e("Tag", "date=$finishDate")
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
fun PickerTime(
    nameText: String,
    onValueChangedHours: (Int) -> Unit,
    onValueChangedMinutes: (Int) -> Unit
) {

    var hours by remember { mutableStateOf(0) }
    var minutes by remember { mutableStateOf(0) }

    Column {
        Row(Modifier.padding(horizontal = 8.dp)) {
            Text(
                "$nameText",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 3.dp)
            )

            Text(
                text = "$hours:$minutes",
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
                        setOnValueChangedListener { _, _, value ->
                           onValueChangedHours.invoke(value)
                            hours = value
                        }//TODO:
                        minValue = 0
                        maxValue = 23
                    }
                }
            )
            AndroidView(
                modifier = Modifier.wrapContentSize(),
                factory = { context ->
                    NumberPicker(context).apply {
                        setOnValueChangedListener { _, _, value ->
                            onValueChangedMinutes.invoke(value)
                            minutes = value
                        }//TODO:
                        minValue = 0
                        maxValue = 59
                    }
                }
            )
        }
    }
}