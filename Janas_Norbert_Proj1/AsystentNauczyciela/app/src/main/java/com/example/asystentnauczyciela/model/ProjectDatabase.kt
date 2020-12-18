package com.example.asystentnauczyciela.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Student::class, Course::class, Mark::class, StudentCourse::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class ProjectDatabase: RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun markDao(): MarkDao

    abstract fun studentCourseDao(): StudentCourseDao

    companion object{
        @Volatile
        private var INSTANCE: ProjectDatabase ?= null

        fun getDatabase(context: Context): ProjectDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }
            else {
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProjectDatabase::class.java,
                        "Project_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                    return instance
                }
            }
        }
    }

}