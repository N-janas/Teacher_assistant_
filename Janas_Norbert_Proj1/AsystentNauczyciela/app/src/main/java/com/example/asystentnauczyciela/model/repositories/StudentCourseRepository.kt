package com.example.asystentnauczyciela.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.*

class StudentCourseRepository(private val studentCourseDao: StudentCourseDao) {

    //val allStudentCourses: List<CourseWithStudents> = studentCourseDao.getCourseWithStudents()

    suspend fun add(studentCourse: StudentCourse){
        studentCourseDao.insertStudentCourse(studentCourse)
    }

    suspend fun delete(studentCourse: StudentCourse){
        studentCourseDao.deleteStudentCourse(studentCourse)
    }

    suspend fun update(studentCourse: StudentCourse){
        studentCourseDao.updateStudentCourse(studentCourse)
    }

    suspend fun selectAllCourseWithStudents(): List<CourseWithStudents>{
        return studentCourseDao.getCourseWithStudents()
    }

    suspend fun getRestOfStudents(currentStudentList: List<Student>): List<Student>{
        return studentCourseDao.getAllStudents()
            .filterNot { currentStudentList.contains(it) }
    }

    suspend fun selectCurrentCourseWithStudents(courseId: Int): List<CourseWithStudents>{
        return studentCourseDao.getCurrentCourseWithStudents(courseId)
    }

    suspend fun selectCurrentStudentWithCourses(studentId: Int): List<StudentWithCourses>{
        return studentCourseDao.getCurrentStudentWithCourses(studentId)
    }

    suspend fun findStudentById(id: Int): Student?{
        return studentCourseDao.findStudentById(id)
    }
//    fun selectCurrentStudentsCourse(courseId: Int): LiveData<List<CourseWithStudents>>{
//
//        return studentCourseDao.selectCurrentStudentsCourse(courseId)
//    }

//    fun selectAll(): LiveData<List<StudentCourse>>{
//        return studentCourseDao.allStudentCourses()
//    }
//
//    suspend fun findStudentCourseById(id: Int): StudentCourse?{
//        return studentCourseDao.findStudentCourseById(id)
//    }
//
//    fun selectCurrentStudentsCourse(courseId: Int): LiveData<List<StudentCourse>>{
//        return studentCourseDao.selectCurrentStudentsCourse(courseId)
//    }

}