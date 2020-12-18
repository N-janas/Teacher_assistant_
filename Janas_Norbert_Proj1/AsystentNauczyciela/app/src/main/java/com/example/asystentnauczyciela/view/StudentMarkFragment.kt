package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.viewmodel.StudentMarkAdapter
import com.example.asystentnauczyciela.viewmodel.StudentMarkViewModel
import kotlinx.android.synthetic.main.fragment_student_mark.*
import java.util.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StudentMarkFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var studentsMarkViewModel: StudentMarkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentsMarkViewModel = ViewModelProvider(requireActivity()).get(StudentMarkViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_mark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null){
            textViewStudentsName.text = StudentMarkFragmentArgs.fromBundle(requireArguments()).nameLastname
        }

        // Fill Spinner
        spinnerMarks.apply{
            adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.mark_system,
                android.R.layout.simple_spinner_item
            )
        }

        buttonAddMark.setOnClickListener {
            var newMark = spinnerMarks.selectedItem
            if (newMark != null){
                var currentStudent = StudentMarkFragmentArgs.fromBundle(requireArguments()).studentsId
                var currentSubject = StudentMarkFragmentArgs.fromBundle(requireArguments()).subjectId

                var markNote = if (editTextMarkNote.text.toString().trim() == "") "Brak notatki" else editTextMarkNote.text.toString().trim()
                studentsMarkViewModel.addMark(Mark(0, newMark.toString(), markNote, Calendar.getInstance().time, currentStudent, currentSubject))

                editTextMarkNote.text.clear()

                Toast.makeText(requireContext(), "Dodano ocenę", Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentMarkFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}