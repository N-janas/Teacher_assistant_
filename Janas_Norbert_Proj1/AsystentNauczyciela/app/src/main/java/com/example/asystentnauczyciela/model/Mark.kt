package com.example.asystentnauczyciela.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "marks_table")
data class Mark(@PrimaryKey(autoGenerate = true) val id: Int,
                var mark: String,
                var markNote: String,
                var markDate: Date,
                var studentId: Int,
                var courseId: Int) {
}