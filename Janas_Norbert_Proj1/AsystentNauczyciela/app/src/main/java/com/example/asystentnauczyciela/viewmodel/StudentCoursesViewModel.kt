package com.example.asystentnauczyciela.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.ProjectDatabase
import com.example.asystentnauczyciela.model.StudentWithCourses
import com.example.asystentnauczyciela.model.repositories.StudentCourseRepository
import kotlinx.coroutines.launch

class StudentCoursesViewModel(application: Application): AndroidViewModel(application) {

    private val studentCourseRepository: StudentCourseRepository =
        StudentCourseRepository(ProjectDatabase.getDatabase(application).studentCourseDao())

    lateinit var studentWithCourses: StudentWithCourses


    private var _currentStudentCourses = MutableLiveData<List<Course>>()

    val currentStudentCourses: LiveData<List<Course>>
            get() = _currentStudentCourses

    init {
        _currentStudentCourses.value = listOf<Course>()
    }

    fun initCurrentStudentWithCourses(studentId: Int){
        viewModelScope.launch {
            studentWithCourses =
                studentCourseRepository.selectCurrentStudentWithCourses(studentId).first()

            _currentStudentCourses.value = studentWithCourses.courseList
        }
    }

}