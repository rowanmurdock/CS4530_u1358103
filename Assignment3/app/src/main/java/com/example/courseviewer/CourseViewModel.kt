package com.example.courseviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class CourseViewModel(private val repository: Repository) : ViewModel() {

    val uiState: StateFlow<List<Course>> =
        repository.allCourses.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addCourse(department: String, courseNum: String, location: String) {
        val newCourse = Course(department = department, courseNum = courseNum, location = location)
        viewModelScope.launch {
            repository.addCourse(newCourse)
        }
    }

    fun deleteCourse(course: Course) {
        viewModelScope.launch {
            repository.deleteCourse(course)
        }
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
