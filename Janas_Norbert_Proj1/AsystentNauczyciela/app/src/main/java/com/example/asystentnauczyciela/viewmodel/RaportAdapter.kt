package com.example.asystentnauczyciela.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Mark
import java.text.DateFormat

class RaportAdapter(var data: LiveData<List<Mark>>): RecyclerView.Adapter<RaportAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.raport_row, parent, false) as View

        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val txtView_MarkSymbol = holder.itemView.findViewById<TextView>(R.id.textViewMarkSymbolRaport)
        val txtView_MarkNote = holder.itemView.findViewById<TextView>(R.id.textViewNoteRaport)
        val txtView_Subject = holder.itemView.findViewById<TextView>(R.id.textViewSubjectRaport)
        val txtView_Student = holder.itemView.findViewById<TextView>(R.id.textViewStudentRaport)

        txtView_MarkSymbol.text = data.value?.get(position)?.mark.toString()
        txtView_MarkNote.text = data.value?.get(position)?.markNote.toString()
        txtView_Subject.text = data.value?.get(position)?.courseId.toString()
        txtView_Student.text = data.value?.get(position)?.studentId.toString()
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }
}