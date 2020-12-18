package com.example.asystentnauczyciela.viewmodel

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
import com.example.asystentnauczyciela.view.AllStudentsFragmentDirections


class AllStudentsAdapter(var data: LiveData<List<Student>>): RecyclerView.Adapter<AllStudentsAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view)

    // Interfejs do komunikacji z ViewModelem żeby nie rozrzucać komunikacji z bazą po całym programie
    private lateinit var listener: DatabaseStudentDelListener

    interface DatabaseStudentDelListener{
        fun onItemDelClicked(student: Student)
    }

    fun setDatabaseStudentDelListener(listener : DatabaseStudentDelListener){
        this.listener = listener
    }
    // ---------------------------------------------------------------------------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.student_row, parent, false) as View

        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val txtView_studentId = holder.itemView.findViewById<TextView>(R.id.textViewStudentId)
        val txtView_studentName = holder.itemView.findViewById<TextView>(R.id.textViewStudentName)
        val txtView_studentLastName = holder.itemView.findViewById<TextView>(R.id.textViewStudentLastName)
        val button_threeDots = holder.itemView.findViewById<ImageButton>(R.id.buttonThreeDotsStudents)

        txtView_studentId.text = data.value?.get(position)?.studentId?.toString() ?: "None"
        txtView_studentName.text = data.value?.get(position)?.name ?: "None"
        txtView_studentLastName.text = data.value?.get(position)?.lastName ?: "None"

        button_threeDots.setOnClickListener{
            showPopupMenu(button_threeDots, position)
        }
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }

    private fun showPopupMenu(view: View, position: Int){
        val popup_menu = PopupMenu(view.context, view)
        popup_menu.inflate(R.menu.popup_menu_student)

        popup_menu.menu.findItem(R.id.action_popup_edit_student).setOnMenuItemClickListener {
            var currStudentId = data.value?.get(position)?.studentId
            if (currStudentId != null){
                val action = AllStudentsFragmentDirections.actionAllStudentsFragmentToEditStudentFragment(currStudentId)
                view.findNavController().navigate(action)
            }
            true
        }

        popup_menu.menu.findItem(R.id.action_popup_marks_student).setOnMenuItemClickListener {
            var currStudentId = data.value?.get(position)?.studentId
            if (currStudentId != null){
                var action2 = AllStudentsFragmentDirections.actionAllStudentsFragmentToStudentsCoursesFragment(
                    currStudentId,
                    data.value?.get(position).toString()
                )
                view.findNavController().navigate(action2)
            }
            true
        }

        popup_menu.menu.findItem(R.id.action_popup_delete_student).setOnMenuItemClickListener {
            var currStudent = data.value?.get(position)
            if (currStudent != null)
                listener.onItemDelClicked(currStudent)
            true
        }
        popup_menu.show()
    }
}