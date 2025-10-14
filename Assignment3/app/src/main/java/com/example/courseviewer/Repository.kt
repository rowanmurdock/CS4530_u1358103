package com.example.courseviewer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class Repository (val scope: CoroutineScope, private val dao: CourseDao) {

    val allCourses: Flow<List<Course>> = dao.getAllCourses()

    fun addCourse(course: Course) {
        scope.launch {
            val courseObj = Course(course.department, course.courseNum, course.location)
            dao.insertCourse(courseObj)
        }
    }

    fun deleteCourse(course: Course) {
        scope.launch {
            val courseObj = Course(course.department, course.courseNum, course.location)
            dao.deleteCourse(courseObj)
        }
    }
}