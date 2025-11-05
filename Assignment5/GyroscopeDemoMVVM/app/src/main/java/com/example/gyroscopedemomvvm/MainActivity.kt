package com.example.gyroscopedemomvvm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gyroscopedemomvvm.ui.theme.GyroscopeDemoTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GyroscopeDemoTheme {
                val myVM: MarbleViewModel = viewModel(factory = MarbleViewModel.Factory)
                MarbleScreen(myVM)
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MarbleScreen(viewModel: MarbleViewModel) {
    val marbleState by viewModel.marbleState.collectAsState()
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val width = with(LocalDensity.current) { maxWidth.toPx() }
        val height = with(LocalDensity.current) { maxHeight.toPx() }
        LaunchedEffect(width, height) {
            viewModel.updateScreenSize(width, height)
        }

        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        marbleState.x.roundToInt(),
                        marbleState.y.roundToInt()
                    )
                }
                .size(100.dp)
                .background(Color.Black, CircleShape)
        )
    }
}