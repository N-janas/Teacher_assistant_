package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.CourseDao

class CourseRepository(private val courseDao: CourseDao) {

    val allCourses: LiveData<List<Course>> = courseDao.allCourses()

    suspend fun add(course: Course){
        courseDao.insertCourse(course)
    }

    suspend fun delete(course: Course){
        courseDao.deleteCourse(course)
    }

    suspend fun update(course: Course){
        courseDao.updateCourse(course)
    }

    fun selectAll(): LiveData<List<Course>>{
        return courseDao.allCourses()
    }

    suspend fun findCourseById(id: Int): Course?{
        return courseDao.findCourseById(id)
    }

}
