package com.example.asystentnauczyciela.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(@PrimaryKey(autoGenerate = true) val studentId: Int, var name: String, var lastName: String) {
    override fun toString(): String {
        return "$name $lastName"
    }
}