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
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.view.SubjectsFragmentDirections

class SubjectsListAdapter(var data: LiveData<List<Course>>): RecyclerView.Adapter<SubjectsListAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view)

    // Interfejs do komunikacji z ViewModelem żeby nie rozrzucać komunikacji z bazą po całym programie
    private lateinit var listener: DatabaseDelListener

    interface DatabaseDelListener{
        fun onItemDelClicked(course: Course)
    }

    fun setDatabaseDelListener(listener : DatabaseDelListener){
        this.listener = listener
    }
    // ---------------------------------------------------------------------------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).
                inflate(R.layout.subject_row, parent, false) as View

        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val txtView_subjectId = holder.itemView.findViewById<TextView>(R.id.txtViewSubjectId)
        val txtView_subjectName = holder.itemView.findViewById<TextView>(R.id.txtviewSubjectName)
        val button_threeDots = holder.itemView.findViewById<ImageButton>(R.id.buttonThreeDotsCourses)

        txtView_subjectId.text = data.value?.get(position)?.courseId.toString()
        txtView_subjectName.text = data.value?.get(position)?.name ?: ""
        button_threeDots.setOnClickListener {
            showPopupMenu(button_threeDots, position)
        }
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }


    private fun showPopupMenu(view: View, position: Int){
        val popup_menu = PopupMenu(view.context, view)
        popup_menu.inflate(R.menu.popup_menu_course)

        popup_menu.menu.findItem(R.id.action_popup_delete_course).setOnMenuItemClickListener {
            var currCourse: Course? = data.value?.get(position)
            if (currCourse != null)
                listener.onItemDelClicked(currCourse)
            true
        }

        popup_menu.menu.findItem(R.id.action_popup_edit_course).setOnMenuItemClickListener {
            var currCourseId = data.value?.get(position)?.courseId
            if (currCourseId != null){
                val action2 = SubjectsFragmentDirections.actionSubjectsFragmentToEditCourseFragment(currCourseId)
                view.findNavController().navigate(action2)
            }
            true
        }

        popup_menu.menu.findItem(R.id.action_popup_participants_course).setOnMenuItemClickListener {
            val subjectId = data.value?.get(position)?.courseId
            val subjectName = data.value?.get(position)?.name ?: "None"
            if (subjectId != null){
                val action = SubjectsFragmentDirections.actionSubjectsFragmentToStudentsCourseFragment(subjectId, subjectName)
                view.findNavController().navigate(action)
            }
            true
        }

        popup_menu.show()
    }

}