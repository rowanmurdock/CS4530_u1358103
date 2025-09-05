package com.example.lec5practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Concatenation()
            }
        }
    }

@Composable
fun Concatenation() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var concat by remember { mutableStateOf("") }

    Column() {
        TextField(
            value = text1,
            onValueChange = { text1 = it },
            label = {"First word"}
        )

        TextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { "Second word" }
        )

        Button(
            onClick = { concat = text1 + text2 }

        )
        {
            Text("Concatenate")
        }

        Text(concat)
    }
}
