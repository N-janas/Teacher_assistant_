package com.example.asystentnauczyciela.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*


@Dao
interface CourseDao {

    @Insert
    suspend fun insertCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)

    @Update
    suspend fun updateCourse(course: Course)

    @Query("SELECT * FROM COURSE_TABLE")
    fun allCourses(): LiveData<List<Course>>

    @Query("SELECT * FROM COURSE_TABLE WHERE courseId = :id LIMIT 1")
    suspend fun findCourseById(id: Int): Course?
}