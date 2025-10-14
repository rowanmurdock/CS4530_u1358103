package com.example.courseviewer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(val department: String,
                      @PrimaryKey() val courseNum: String,
                      val location: String)