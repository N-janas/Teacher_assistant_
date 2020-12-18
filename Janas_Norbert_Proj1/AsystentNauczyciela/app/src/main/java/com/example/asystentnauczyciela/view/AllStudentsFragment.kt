package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.viewmodel.AllStudentsAdapter
import com.example.asystentnauczyciela.viewmodel.AllStudentsViewModel
import kotlinx.android.synthetic.main.fragment_all_students.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AllStudentsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var studentsViewModel: AllStudentsViewModel
    private lateinit var studentsAdapter: AllStudentsAdapter
    private lateinit var studentsViewManager: RecyclerView.LayoutManager

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
        // Initializing ViewModel for Students
        studentsViewModel = ViewModelProvider(requireActivity()).get(AllStudentsViewModel::class.java)

        studentsViewManager = LinearLayoutManager(requireContext())
        studentsAdapter = AllStudentsAdapter(studentsViewModel.students)
        studentsAdapter.setDatabaseStudentDelListener(studentsViewModel)

        studentsViewModel.students.observe(viewLifecycleOwner, Observer {
            studentsAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_students, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Apply Adapter and Layout
        recyclerView_students.apply {
            adapter = studentsAdapter
            layoutManager = studentsViewManager
        }

        // Button Add to students
        buttonAddStudent.setOnClickListener {
            var name = editTextPersonName.text.toString()
            var lastName = editTextPersonLastName.text.toString()

            if (studentsViewModel.isNotEmptyOrBlank(name) &&
                studentsViewModel.isNotEmptyOrBlank(lastName))
            {
                studentsViewModel.addStudent(name, lastName)

                editTextPersonName.text.clear()
                editTextPersonLastName.text.clear()

            } else
                Toast.makeText(requireContext(), "Niepoprawne dane", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllStudentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}