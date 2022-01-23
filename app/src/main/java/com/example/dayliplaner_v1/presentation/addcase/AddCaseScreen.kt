package com.example.dayliplaner_v1.presentation.addcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun MainScreen() {
    val name = remember{ mutableStateOf("") }
    val description = remember{ mutableStateOf("") }
    val time = remember{ mutableStateOf("") }

    Column() {
        Button(

            onClick = { /*TODO*/ }) {
            Text(text = "Back")
        }
        MyTextFild(
            value = name.value,
            onValueChange ={name.value = it},
            placeHolder = "Enter name" ,
            error = "Is not empty")
        MyTextFild(
            value = description.value,
            onValueChange ={description.value = it},
            placeHolder = "Enter description" ,
            error = "Is not empty")
        MyTextFild(
            value = time.value,
            onValueChange ={time.value = it},
            placeHolder = "Enter time" ,
            error = "Is not empty")
        Button(

            onClick = { /*TODO*/ }) {
            Text(text = "Save")
        }
    }
}

@Composable
fun MyTextFild(
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

@Preview(showSystemUi = true, showBackground = true, name  = "AddCaseScreen")
@Composable
fun DefaultPreview() {

    MainScreen()
}