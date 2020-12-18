package com.example.asystentnauczyciela.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import java.util.*

@Dao
interface MarkDao {

    @Insert
    suspend fun insertMark(mark: Mark)

    @Delete
    suspend fun  deleteMark(mark: Mark)

    @Update
    suspend fun updateMark(mark: Mark)

    @Query("SELECT * FROM MARKS_TABLE")
    fun allMarks(): LiveData<List<Mark>>

    @Query("SELECT * FROM MARKS_TABLE WHERE id = :id LIMIT 1")
    suspend fun findMarkById(id: Int): Mark?

    @Query("SELECT * FROM marks_table WHERE studentId = :studentId AND courseId = :courseId")
    suspend fun findMarkStudentCourse(studentId: Int, courseId: Int): List<Mark>

    @Query("SELECT * FROM marks_table WHERE markDate BETWEEN :from AND :to")
    suspend fun findMarkByDate(from: Date, to: Date): List<Mark>

}