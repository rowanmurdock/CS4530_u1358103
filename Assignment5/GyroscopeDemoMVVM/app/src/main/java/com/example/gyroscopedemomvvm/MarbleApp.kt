package com.example.gyroscopedemomvvm

import android.app.Application
import android.hardware.SensorManager

class MarbleApp : Application() {

    val sensorManager: SensorManager by lazy {
        getSystemService(SENSOR_SERVICE) as SensorManager
    }

    val marbleRepository by lazy {
        MarbleRepository(sensorManager)
    }
}
