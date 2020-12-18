package com.example.asystentnauczyciela.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CourseWithStudents (
    @Embedded val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(StudentCourse::class)
    ) val studentList: List<Student>
)