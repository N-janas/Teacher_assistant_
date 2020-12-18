package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.viewmodel.AllStudentsViewModel
import com.example.asystentnauczyciela.viewmodel.StudentsCourseAdapter
import com.example.asystentnauczyciela.viewmodel.StudentsCourseViewModel
import kotlinx.android.synthetic.main.fragment_students_group.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StudentsCourseFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var studentsCourseViewModel: StudentsCourseViewModel
    private lateinit var studentsCourseAdapter: StudentsCourseAdapter
    private lateinit var studentsCourseViewManager: RecyclerView.LayoutManager


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
        studentsCourseViewModel = ViewModelProvider(requireActivity()).get(StudentsCourseViewModel::class.java)

        studentsCourseAdapter = StudentsCourseAdapter(studentsCourseViewModel.studentsFromCourse)
        studentsCourseAdapter.setDatabaseStudentCourseDelListener(studentsCourseViewModel)

        // Arguments -- Id of subject
        if (arguments != null){
            val currentSubject = StudentsCourseFragmentArgs.fromBundle(requireArguments()).currentSubjectId
            // Load students assigned to current subject AND set current Subject
            studentsCourseViewModel.setCurrentSubject(currentSubject)

            studentsCourseAdapter.currentGroup = studentsCourseViewModel.currentSubject
            studentsCourseViewModel.initCurrentCourseWithStudents()

        }

        studentsCourseViewManager = LinearLayoutManager(requireContext())

        studentsCourseViewModel.studentsFromCourse.observe(viewLifecycleOwner, Observer {
            studentsCourseAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_studentsGroup.apply{
            adapter = studentsCourseAdapter
            layoutManager = studentsCourseViewManager
        }

        if (arguments != null){
            textViewStudentCoursetitle.text = StudentsCourseFragmentArgs.fromBundle(requireArguments()).subjectTitle

            buttonStudentAssign.setOnClickListener {
                view -> view.findNavController().navigate(R.id.action_studentsCourseFragment_to_addStudentToCourseFragment)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentsCourseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}