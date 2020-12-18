package com.example.asystentnauczyciela.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class Course(@PrimaryKey(autoGenerate = true) val courseId:Int, var name: String) {
}