package com.example.asystentnauczyciela.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentCourseDao {

    @Insert
    suspend fun insertStudentCourse(studentCourse: StudentCourse)

    @Delete
    suspend fun deleteStudentCourse(studentCourse: StudentCourse)

    @Update
    suspend fun updateStudentCourse(studentCourse: StudentCourse)

    @Query("SELECT * FROM STUDENT_TABLE WHERE studentId = :id LIMIT 1")
    suspend fun findStudentById(id: Int): Student?

    @Transaction
    @Query("SELECT  * FROM course_table")
    suspend fun getCourseWithStudents(): List<CourseWithStudents>

    @Transaction
    @Query("SELECT  * FROM course_table WHERE courseId = :courseId")
    suspend fun getCurrentCourseWithStudents(courseId: Int): List<CourseWithStudents>

    @Transaction
    @Query("SELECT * FROM STUDENT_TABLE WHERE studentId = :studentId")
    suspend fun getCurrentStudentWithCourses(studentId: Int): List<StudentWithCourses>

    @Query("SELECT * FROM student_table")
    suspend fun getAllStudents(): List<Student>

}