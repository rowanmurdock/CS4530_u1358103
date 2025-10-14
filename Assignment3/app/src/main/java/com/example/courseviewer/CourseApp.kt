package com.example.courseviewer

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CourseApp: Application() {
    val scope =CoroutineScope(SupervisorJob())
    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "courseDB"
        ).build()
    }

    val repository by lazy { Repository(scope, db.courseDao()) }

}