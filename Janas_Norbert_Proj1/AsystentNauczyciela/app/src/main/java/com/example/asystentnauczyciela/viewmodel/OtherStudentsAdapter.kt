package com.example.asystentnauczyciela.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student

class OtherStudentsAdapter(var data: LiveData<ArrayList<Student>>): RecyclerView.Adapter<OtherStudentsAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view)


    private lateinit var listener: AddOtherStudentListener

    interface AddOtherStudentListener{
        fun onItemAddClickListener(studentId: Int)
    }

    fun setAddOtherStudentListener(listener: AddOtherStudentListener){
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.another_course_student_row, parent, false) as View

        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val txtView_studentId = holder.itemView.findViewById<TextView>(R.id.textViewOtherStudentId)
        val txtView_studentName = holder.itemView.findViewById<TextView>(R.id.textViewOtherStudentName)
        val txtView_studentLastName = holder.itemView.findViewById<TextView>(R.id.textViewOtherStudentLastName)
        val button_addStudent = holder.itemView.findViewById<Button>(R.id.buttonAddOtherStudent)

        txtView_studentId.text = data.value?.get(position)?.studentId.toString()
        txtView_studentName.text = data.value?.get(position)?.name.toString()
        txtView_studentLastName.text = data.value?.get(position)?.lastName.toString()

        button_addStudent.setOnClickListener {
            if (data.value?.get(position)?.studentId != null){
                listener.onItemAddClickListener(data.value?.get(position)?.studentId!!)
                data.value?.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }
}