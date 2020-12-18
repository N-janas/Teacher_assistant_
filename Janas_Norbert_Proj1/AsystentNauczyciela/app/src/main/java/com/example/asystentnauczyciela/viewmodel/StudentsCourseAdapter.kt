package com.example.asystentnauczyciela.viewmodel

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentCourse
import com.example.asystentnauczyciela.view.StudentsCourseFragmentDirections

class StudentsCourseAdapter(var data: LiveData<ArrayList<Student>>): RecyclerView.Adapter<StudentsCourseAdapter.Holder>()
{
    class Holder(view: View): RecyclerView.ViewHolder(view)

    var currentGroup: Int? = null


    private lateinit var listener: DatabaseStudentCourseDelListener

    interface DatabaseStudentCourseDelListener{
        fun onItemDelClicked(studentId: Int)
        fun getCurrentSubject(): Int
    }

    fun setDatabaseStudentCourseDelListener(listener: DatabaseStudentCourseDelListener){
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.student_marking_row, parent, false) as View

        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: StudentsCourseAdapter.Holder, position: Int) {
        val txtView_studentId = holder.itemView.findViewById<TextView>(R.id.textViewIdwithM)
        val txtView_studentName = holder.itemView.findViewById<TextView>(R.id.textViewNamewithM)
        val txtView_studentLastName = holder.itemView.findViewById<TextView>(R.id.textViewLastNamewithM)
        val button_threeDots = holder.itemView.findViewById<ImageButton>(R.id.buttonThreDotsStudentCourse)

        txtView_studentId.text = data.value?.get(position)?.studentId?.toString() ?: "None"
        txtView_studentName.text = data.value?.get(position)?.name ?: ""
        txtView_studentLastName.text = data.value?.get(position)?.lastName ?: ""

        button_threeDots.setOnClickListener {
            ShowPopupMenu(button_threeDots, position)
        }
    }

    private fun ShowPopupMenu(view: View, position: Int){
        val popup_menu = PopupMenu(view.context, view)
        popup_menu.inflate(R.menu.popup_menu_student_group)

        popup_menu.menu.findItem(R.id.action_popup_deleteFromCourse).setOnMenuItemClickListener {
            var currStudent = data.value?.get(position)
            if (currStudent != null){
                listener.onItemDelClicked(currStudent.studentId)
                data.value?.removeAt(position)
                // można zastąpić refreshowaniem z bazy, ale czy warto ?
                notifyDataSetChanged()
            }
            true
        }

        popup_menu.menu.findItem(R.id.action_popup_markTheStudent).setOnMenuItemClickListener {
            val subjectId: Int = listener.getCurrentSubject()

            val action = StudentsCourseFragmentDirections.actionStudentsCourseFragmentToStudentMarkFragment(
                data.value?.get(position)?.studentId!!,
                data.value?.get(position).toString(),
                subjectId
            )

            view.findNavController().navigate(action)
            true
        }

        popup_menu.show()
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }


}