package com.example.asystentnauczyciela.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentWithCourses (
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "courseId",
        associateBy = Junction(StudentCourse::class)
    ) val courseList: List<Course>
)
