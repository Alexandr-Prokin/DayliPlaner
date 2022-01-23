package com.example.dayliplaner_v1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.dayliplaner_v1.presentation.addcase.AddCaseViewModel

class AddCaseScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

private var addCaseViewModel = AddCaseViewModel()

@Composable
fun MainScreen() {
    Column() {
        Button(

            onClick = { /*TODO*/ }) {
            Text(text = "Back")
        }
        Text(text = "Name: ")
        Text(text = "Description: ")
        Text(text = "Time: ")
        Button(

            onClick = { /*TODO*/ }) {
            Text(text = "Save")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {

    MainScreen()
}
