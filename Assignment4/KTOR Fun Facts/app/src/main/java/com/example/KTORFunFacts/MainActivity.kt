package com.example.KTORFunFacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.KTORFunFacts.ui.theme.ComposeDEMOTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDEMOTheme {
               val vm:FunFactViewModel = viewModel(factory = FunFactViewModelProvider.Factory)
               FactList(vm)
            }
        }
    }
}

@Composable
fun FactList(vm: FunFactViewModel) {
    Column(
        Modifier.fillMaxWidth().padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val observableList by vm.facts.collectAsState()

        Row {
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch()
                {
                    vm.addNewFunFact()
                }
            }) {
                Text("Get New Fact")

            }

        }

        Spacer(Modifier.height(20.dp))
        Text(
            "Fun Facts",
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Blue
        )
        Row {
            LazyColumn {
                items(observableList) { fact ->
                    Text(
                        fact.text, fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}

