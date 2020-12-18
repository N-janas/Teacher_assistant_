package com.example.asystentnauczyciela.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.asystentnauczyciela.model.ProjectDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.repositories.StudentRepository
import kotlinx.coroutines.launch

class AllStudentsViewModel(application: Application): AndroidViewModel(application),
    AllStudentsAdapter.DatabaseStudentDelListener{

    override fun onItemDelClicked(student: Student) {
        deleteStudent(student)
    }

    private val _students: LiveData<List<Student>> =
        ProjectDatabase.getDatabase(application).studentDao().allStudents()

    private val studentRepository: StudentRepository =
        StudentRepository(ProjectDatabase.getDatabase(application).studentDao())

    val students: LiveData<List<Student>>
        get() = _students


    fun addStudent(name: String, lastName: String){
        Log.d("logi", "Dodano nowego studenta")
        viewModelScope.launch {
            studentRepository.add(Student(studentId = 0, name = name.trim(), lastName = lastName.trim()))
        }
    }

    fun deleteStudent(student: Student){
        Log.d("logi", "Usunieto studenta")
        viewModelScope.launch {
            studentRepository.delete(student)
        }
    }

    fun updateStudent(id: Int, newName: String, newLastName: String){
        Log.d("logi", "Edytowano studenta")
        val student = getStudentById(id)

        if (student != null){
            student.name = newName.trim()
            student.lastName= newLastName.trim()

            viewModelScope.launch {
                studentRepository.update(student)
            }
        }
    }

    fun getStudentById(id: Int): Student?{
        return students.value?.firstOrNull{obj -> obj.studentId == id}
    }

    fun isNotEmptyOrBlank(text: String): Boolean{
        return (text.isNotEmpty() && text.isNotBlank())
    }



}