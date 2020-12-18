package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.viewmodel.StudentCoursesAdapter
import com.example.asystentnauczyciela.viewmodel.StudentCoursesViewModel
import kotlinx.android.synthetic.main.fragment_students_courses.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StudentsCoursesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var studentCoursesViewModel: StudentCoursesViewModel
    private lateinit var studentCoursesAdapter: StudentCoursesAdapter
    private lateinit var studentCoursesViewManager: RecyclerView.LayoutManager

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
        studentCoursesViewModel = ViewModelProvider(requireActivity()).get(StudentCoursesViewModel::class.java)

        if (arguments != null){
            var selectedStudent = StudentsCoursesFragmentArgs.fromBundle(requireArguments()).studentId
            studentCoursesAdapter = StudentCoursesAdapter(
                studentCoursesViewModel.currentStudentCourses,
                selectedStudent,
                StudentsCoursesFragmentArgs.fromBundle(requireArguments()).studentNameLastName
            )
            studentCoursesViewModel.initCurrentStudentWithCourses(selectedStudent)
        }

        studentCoursesViewManager = LinearLayoutManager(requireContext())

        studentCoursesViewModel.currentStudentCourses.observe(viewLifecycleOwner, Observer {
            studentCoursesAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_studentCourses.apply {
            layoutManager = studentCoursesViewManager
            adapter = studentCoursesAdapter
        }

        if (arguments != null){
            textViewCurrentStudentCourses.text = resources.getString(
                R.string.concatTitleAndStudent,
                StudentsCoursesFragmentArgs.fromBundle(requireArguments()).studentNameLastName
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentsCoursesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}