package com.example.gyroscopedemomvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

data class MarbleState(
    val x: Float = 0f,
    val y: Float = 0f,
    val velx: Float = 0f,
    val vely: Float = 0f
)

class MarbleViewModel(private val repository: MarbleRepository) : ViewModel() {
    private val _marbleState = MutableStateFlow(MarbleState())
    val marbleState: StateFlow<MarbleState> = _marbleState.asStateFlow()

    private var screenWidth = 0f
    private var screenHeight = 0f

    fun updateScreenSize(width: Float, height: Float) {
        screenWidth = width
        screenHeight = height
    }

    init {
        viewModelScope.launch {
            repository.getGravFlow().collect { reading ->
                updatePosition(reading)
            }
        }
    }

    private fun updatePosition(reading: GravityReading) {
        val dt = 0.2f
        val scale = -10f

        var velx = _marbleState.value.velx + dt * scale * reading.x
        var vely = _marbleState.value.vely + dt * scale * -reading.y

        velx *= 0.95f
        vely *= 0.95f

        var x = _marbleState.value.x + velx * dt
        var y = _marbleState.value.y + vely * dt

        val marbleRadius = 50f
        x = min(max(x, -screenWidth / 2 + marbleRadius), screenWidth / 2 - marbleRadius)
        y = min(max(y, -screenHeight / 2 + marbleRadius), screenHeight / 2 - marbleRadius)

        _marbleState.value = MarbleState(x, y, velx, vely)
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarbleApp)
                MarbleViewModel(application.marbleRepository)
            }
        }
    }
}


