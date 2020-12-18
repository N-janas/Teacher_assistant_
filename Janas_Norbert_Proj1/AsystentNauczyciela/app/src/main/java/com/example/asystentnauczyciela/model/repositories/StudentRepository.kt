package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentDao

class StudentRepository(private val studentDao: StudentDao) {

    val allStudents: LiveData<List<Student>> = studentDao.allStudents()

    suspend fun add(student: Student){
        studentDao.insertStudent(student)
    }

    suspend fun delete(student: Student){
        studentDao.deleteStudent(student)
    }

    suspend fun update(student: Student){
        studentDao.updateStudent(student)
    }

    fun selectAll(): LiveData<List<Student>>{
        return studentDao.allStudents()
    }

    suspend fun findStudentById(id: Int): Student?{
        return studentDao.findStudentById(id)
    }

}