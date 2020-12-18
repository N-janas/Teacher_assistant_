package com.example.asystentnauczyciela.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.ProjectDatabase
import com.example.asystentnauczyciela.model.repositories.CourseRepository
import com.example.asystentnauczyciela.model.repositories.MarkRepository
import com.example.asystentnauczyciela.model.repositories.StudentRepository
import kotlinx.coroutines.launch
import java.util.*

class RaportViewModel(application: Application): AndroidViewModel(application) {

    private val markRepository =
        MarkRepository(ProjectDatabase.getDatabase(application).markDao())


    private var _marksRaport = MutableLiveData<List<Mark>>()

    val marksRaport: LiveData<List<Mark>>
        get() = _marksRaport



    init {
        _marksRaport.value = listOf<Mark>()

    }

    fun initMarksRaport(){
        var start = Calendar.getInstance()
        start.set(
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DATE),
            0,
            0,
            0
        )

        var stop = Calendar.getInstance()
        stop.set(
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DATE),
            23,
            59,
            59
        )

        viewModelScope.launch {
            _marksRaport.value = markRepository.findMarkByDate(start.time, stop.time)

        }
    }

}