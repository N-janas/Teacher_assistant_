package com.example.asystentnauczyciela.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.ProjectDatabase
import com.example.asystentnauczyciela.model.repositories.MarkRepository
import kotlinx.coroutines.launch

class MarksOfStudentViewModel(application: Application): AndroidViewModel(application) {

    val markTypes: Map<String, Int> = mapOf(
        "Ndst" to 2,
        "Dst" to 3,
        "Db" to 4,
        "Bdb" to 5
    )

    private var _average = MutableLiveData<String>()

    val average: LiveData<String>
        get() = _average

    private val markRepository =
        MarkRepository(ProjectDatabase.getDatabase(application).markDao())

    private var _marksStudentCourse = MutableLiveData<List<Mark>>()

    val markStudentCourse: LiveData<List<Mark>>
        get() = _marksStudentCourse

    init {
        _average.value = getApplication<Application>().resources.getString(R.string.average_mark_none)
        _marksStudentCourse.value = listOf<Mark>()
    }

    fun initMarkStudentCourse(studentId: Int, courseId: Int){
        viewModelScope.launch {
            _marksStudentCourse.value = markRepository.selectMarkStudentCourse(studentId, courseId)
            var tmpAverage: Float = 0f
            for (mark in _marksStudentCourse.value!!){
                tmpAverage += markTypes[mark.mark] ?: 0
            }

            if (tmpAverage == 0f){
                _average.value = getApplication<Application>().resources.getString(R.string.average_mark,
                    getApplication<Application>().resources.getString(R.string.average_mark_none))
            } else{
                _average.value = getApplication<Application>().resources.getString(
                    R.string.average_mark, "%.2f".format(tmpAverage/_marksStudentCourse.value!!.size))
            }
        }
    }

}