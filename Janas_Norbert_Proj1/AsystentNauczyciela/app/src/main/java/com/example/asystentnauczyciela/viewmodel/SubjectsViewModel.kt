package com.example.asystentnauczyciela.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.ProjectDatabase
import com.example.asystentnauczyciela.model.repositories.CourseRepository
import kotlinx.coroutines.launch

class SubjectsViewModel(application: Application): AndroidViewModel(application), SubjectsListAdapter.DatabaseDelListener {

    override fun onItemDelClicked(course: Course) {
        deleteCourse(course)
    }


    private val _subjects: LiveData<List<Course>> =
        ProjectDatabase.getDatabase(application).courseDao().allCourses()

    private val subjectRepository: CourseRepository =
        CourseRepository(ProjectDatabase.getDatabase(application).courseDao())

    val subjects: LiveData<List<Course>>
        get() = _subjects

    fun addCourse(name: String){
        Log.d("logi", "Dodano nowy kurs")
        viewModelScope.launch {
            subjectRepository.add(Course(courseId = 0, name = name.trim()))
        }
    }

    fun deleteCourse(course: Course){
        Log.d("logi", "Usunięto kurs")
        viewModelScope.launch {
            subjectRepository.delete(course)
        }
    }

    fun updateCourse(id: Int, newName: String){
        Log.d("logi", "Edytowano kurs")
        val course = getCourseById(id)

        if (course != null){
            course.name = newName.trim()

            viewModelScope.launch {
                subjectRepository.update(course)
            }
        }
    }

    fun getCourseById(id: Int): Course?{
        return subjects.value?.firstOrNull{obj -> obj.courseId == id }
    }

    fun isNotEmptyOrBlankOrExists(name: String): Boolean{
        // Is empty or blank
        if (name.isNotEmpty() && name.isNotBlank()){
            // Not exists yet
            return subjects.value?.any{ obj -> obj.name == name.trim() } != true
        } else
            return false
    }

}
