package com.example.asystentnauczyciela.viewmodel

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.view.StudentsCoursesFragmentDirections

class StudentCoursesAdapter(var data: LiveData<List<Course>>, var studentId: Int, var studentName: String) :
    RecyclerView.Adapter<StudentCoursesAdapter.Holder>()  {

    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_displayonly_row, parent, false) as View

        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val txtView_subjectId = holder.itemView.findViewById<TextView>(R.id.txtViewDisplaySubjectId)
        val txtView_subjectName = holder.itemView.findViewById<TextView>(R.id.txtviewSubjectNameDisplayOnly)

        txtView_subjectId.text = data.value?.get(position)?.courseId.toString()
        txtView_subjectName.text = data.value?.get(position)?.name ?: ""

        holder.itemView.setOnClickListener {
            var currentSubject = data.value?.get(position)
            if (currentSubject != null){
                var action = StudentsCoursesFragmentDirections.actionStudentsCoursesFragmentToMarksOfStudentFragment(
                    studentId,
                    data.value?.get(position)?.courseId!!,
                    studentName,
                    data.value?.get(position)?.name!!
                )

                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }
}