package com.example.dayliplaner_v1.presentation.addcase

import android.os.Bundle
import android.widget.CalendarView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController
import com.example.dayliplaner_v1.presentation.addcase.theme.DayliPlaner_v1Theme

var addCaseViewModel = AddCaseViewModel()

class AddCaseScreen : ComponentActivity() {
    var addCaseViewModel = AddCaseViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        // val viewModel = ViewModelProviders.of(this)[AddCaseViewModel::class.java]
        super.onCreate(savedInstanceState)

        setContent {
            DayliPlaner_v1Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(addCaseViewModel)
                }
            }
        }
    }
}
@Composable
fun MyCalendarView() {
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
fun MainScreen(addCaseViewModel: AddCaseViewModel) {
    val navController = rememberNavController()
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    addCaseViewModel.caseRecordModel.name = name.value
    addCaseViewModel.caseRecordModel.description = description.value
    // addCaseViewModel.caseRecordModel.dateStart.hours = remember{ mutableStateOf("") }

    val time = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(2), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Button(

                onClick = {
                    navController
                        .navigate(
                            "calendarFragment"
                        )
                }
            ) {
                Text(text = "Back")
            }
        }

        MyCalendarView()

        AppTextFild(
            value = name.value,
            onValueChange = { name.value = it },
            placeHolder = "Enter name",
            error = "Is not empty"
        )
        AppTextFild(
            value = description.value,
            onValueChange = { description.value = it },
            placeHolder = "Enter description",
            error = "Is not empty"
        )
        Row {
            PickerTime()
            Spacer(modifier = Modifier.size(10.dp))
            PickerTime()
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(

            onClick = {
                addCaseViewModel.saveCase()
                navController.navigate("calendarFragment")
            }

        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun AppTextFild(
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
// @Preview(showSystemUi = true, name = "AddCaseScreen")
// @Composable
// fun DefaultPreview() {
//
//    MainScreen(addCaseViewModel)
// }
