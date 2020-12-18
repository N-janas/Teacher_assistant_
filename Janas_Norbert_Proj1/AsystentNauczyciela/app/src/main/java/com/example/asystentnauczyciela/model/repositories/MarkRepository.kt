package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.MarkDao
import java.util.*

class MarkRepository(private val markDao: MarkDao) {

    val allMarks: LiveData<List<Mark>> = markDao.allMarks()

    suspend fun add(mark: Mark){
        markDao.insertMark(mark)
    }

    suspend fun delete(mark: Mark){
        markDao.deleteMark(mark)
    }

    suspend fun update(mark: Mark){
        markDao.updateMark(mark)
    }

    fun selectAll(): LiveData<List<Mark>>{
        return markDao.allMarks()
    }

    suspend fun findMarkById(id: Int): Mark?{
        return markDao.findMarkById(id)
    }

    suspend fun selectMarkStudentCourse(studentId: Int, courseId: Int): List<Mark>{
        return markDao.findMarkStudentCourse(studentId, courseId)
    }

    suspend fun findMarkByDate(from: Date, to: Date): List<Mark>{
        return markDao.findMarkByDate(from, to)
    }
}