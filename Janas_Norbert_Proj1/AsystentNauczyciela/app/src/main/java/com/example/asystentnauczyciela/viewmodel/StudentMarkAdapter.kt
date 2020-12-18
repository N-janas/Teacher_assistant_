package com.example.asystentnauczyciela.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Mark
import java.text.DateFormat
import java.time.format.DateTimeFormatter


class StudentMarkAdapter(var data: LiveData<List<Mark>>): RecyclerView.Adapter<StudentMarkAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.mark_row, parent, false) as View

        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val txtView_MarkSymbol = holder.itemView.findViewById<TextView>(R.id.textViewMarkSymbol)
        val txtView_MarkNote = holder.itemView.findViewById<TextView>(R.id.textViewNoteM)
        val txtView_MarkDate = holder.itemView.findViewById<TextView>(R.id.textViewMarkDateM)

        txtView_MarkSymbol.text = data.value?.get(position)?.mark.toString()
        txtView_MarkNote.text = data.value?.get(position)?.markNote.toString()
        txtView_MarkDate.text = DateFormat.getDateInstance(DateFormat.SHORT).format(data.value?.get(position)?.markDate!!)
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }
}