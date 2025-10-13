package com.example.courseviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CourseViewModel(private val repository: Repository) : ViewModel() {
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


object CourseViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CourseApp
            CourseViewModel(application.repository)
        }
    }
}
