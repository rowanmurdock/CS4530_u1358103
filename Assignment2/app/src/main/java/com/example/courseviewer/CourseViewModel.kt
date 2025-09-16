package com.example.courseviewer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Course(
    val department: String,
    val courseNum: String,
    val location: String,
)

class CourseViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<List<Course>>(emptyList())
    val uiState: StateFlow<List<Course>> = _uiState.asStateFlow()

    fun addCourse(department: String, courseNum: String, location: String){
        val newCourse = Course(department, courseNum, location)
        _uiState.value += newCourse
    }

    fun deleteCourse(courseNum: String){
        _uiState.value = _uiState.value.filter { it.courseNum != courseNum}
    }

}