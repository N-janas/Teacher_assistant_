package com.example.asystentnauczyciela.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.ProjectDatabase
import com.example.asystentnauczyciela.model.repositories.MarkRepository
import kotlinx.coroutines.launch

class StudentMarkViewModel(application: Application): AndroidViewModel(application) {

    private val markRepository: MarkRepository =
        MarkRepository(ProjectDatabase.getDatabase(application).markDao())

    fun addMark(mark: Mark){
        viewModelScope.launch {
            markRepository.add(mark)
        }
    }
}