package com.example.dayliplaner_v1.presentation.addcase

import android.content.Context
import android.os.Bundle
import android.widget.CalendarView
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController
import com.example.dayliplaner_v1.presentation.addcase.theme.DayliPlaner_v1Theme

class AddCaseScreen : ComponentActivity() {
    var addCaseViewModel = AddCaseViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DayliPlaner_v1Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(addCaseViewModel, this)
                }
            }
        }
    }
}
@Composable
fun MyCalendarView(addCaseViewModel: AddCaseViewModel) {
    AndroidView(
        { CalendarView(it) },
        Modifier.fillMaxWidth(),
        update = { view ->
            view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                addCaseViewModel.setDay(year, month + 1, dayOfMonth)
            }
        }
    )
}
@Composable
fun MainScreen(addCaseViewModel: AddCaseViewModel, context: Context) {
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
        Row() {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth()
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
        Button(
            onClick = {
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
            Text(text = "Save")
        }
    }
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

//
@Preview(showSystemUi = true, name = "AddCaseScreen")
@Composable
fun DefaultPreview() {
    val addCaseViewModel = AddCaseViewModel()

    // MainScreen(addCaseViewModel, context = Context)
}
