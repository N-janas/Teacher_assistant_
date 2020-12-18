package com.example.asystentnauczyciela.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["studentId", "courseId"])
data class StudentCourse(var studentId: Int, var courseId: Int) {
}