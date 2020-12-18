package com.example.asystentnauczyciela.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.*
import com.example.asystentnauczyciela.model.repositories.StudentCourseRepository
import kotlinx.coroutines.launch

class StudentsCourseViewModel(application: Application): AndroidViewModel(application),
    StudentsCourseAdapter.DatabaseStudentCourseDelListener, OtherStudentsAdapter.AddOtherStudentListener {

    override fun onItemDelClicked(studentId: Int) {
        deleteStudentCourse(studentId)
    }

    override fun onItemAddClickListener(studentId: Int) {
        addStudentCourse(studentId)
    }

    override fun getCurrentSubject(): Int {
        return currentSubject!!
    }


    private var _currentSubject: Int? = null

    val currentSubject: Int?
        get() = _currentSubject

    fun setCurrentSubject(subId: Int){
        _currentSubject = subId
    }


    private val studentCourseRepository: StudentCourseRepository =
        StudentCourseRepository(ProjectDatabase.getDatabase(application).studentCourseDao())


    private lateinit var currentCourseWithStudents: CourseWithStudents

    fun initCurrentCourseWithStudents(){
        if (currentSubject != null)
            viewModelScope.launch {
                currentCourseWithStudents =
                    studentCourseRepository.selectCurrentCourseWithStudents(currentSubject!!).first()

                _studentsFromCourse.value = ArrayList(currentCourseWithStudents.studentList)

                // Init rest of students
                _restOfStudents.value = ArrayList(studentCourseRepository
                    .getRestOfStudents(currentCourseWithStudents.studentList))
            }
    }

    private var _restOfStudents = MutableLiveData<ArrayList<Student>>()

    val restOfStudents: LiveData<ArrayList<Student>>
        get() = _restOfStudents


    private var _studentsFromCourse = MutableLiveData<ArrayList<Student>>()

    val studentsFromCourse: LiveData<ArrayList<Student>>
        get() = _studentsFromCourse


    init {

        _studentsFromCourse.value = arrayListOf<Student>()
        _restOfStudents.value = arrayListOf<Student>()
    }

    fun addStudentCourse(studentId: Int){
        var subjectId: Int = currentCourseWithStudents.course.courseId
        viewModelScope.launch {
            studentCourseRepository.add(StudentCourse(studentId = studentId, courseId = subjectId))
        }
    }

    fun deleteStudentCourse(studentId: Int){
        viewModelScope.launch {
            studentCourseRepository.delete(
                StudentCourse(
                    studentId = studentId,
                    courseId = currentCourseWithStudents.course.courseId
                )
            )

            val deletedStudent = studentCourseRepository.findStudentById(studentId)
            if (deletedStudent != null){
                _restOfStudents.value?.add(
                    deletedStudent
                )
            }
        }
    }

    fun updateStudentCourse(){
        // Dead code
    }

}