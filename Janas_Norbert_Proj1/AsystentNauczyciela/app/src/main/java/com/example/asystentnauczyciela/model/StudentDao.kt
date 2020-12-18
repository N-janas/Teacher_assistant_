package com.example.asystentnauczyciela.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Query("SELECT * FROM STUDENT_TABLE")
    fun allStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM STUDENT_TABLE WHERE studentId = :id LIMIT 1")
    suspend fun findStudentById(id: Int): Student?
}