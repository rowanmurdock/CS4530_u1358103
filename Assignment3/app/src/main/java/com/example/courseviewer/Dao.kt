package com.example.courseviewer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse (course: Course)

    @Delete()
    suspend fun deleteCourse (course: Course)

    @Query("select * from courses")
    fun getAllCourses(): Flow<List<Course>>
}