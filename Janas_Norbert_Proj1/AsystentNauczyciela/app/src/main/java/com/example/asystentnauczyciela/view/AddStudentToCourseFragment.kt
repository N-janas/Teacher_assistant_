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
import com.example.asystentnauczyciela.viewmodel.OtherStudentsAdapter
import com.example.asystentnauczyciela.viewmodel.StudentsCourseAdapter
import com.example.asystentnauczyciela.viewmodel.StudentsCourseViewModel
import kotlinx.android.synthetic.main.fragment_add_student_to_course.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddStudentToCourseFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var studentsCourseViewModel: StudentsCourseViewModel
    private lateinit var addStudentToCourseAdapter: OtherStudentsAdapter
    private lateinit var addStudentToCourseViewManager: RecyclerView.LayoutManager

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

        addStudentToCourseAdapter = OtherStudentsAdapter(studentsCourseViewModel.restOfStudents)
        addStudentToCourseAdapter.setAddOtherStudentListener(studentsCourseViewModel)

        addStudentToCourseViewManager = LinearLayoutManager(requireContext())

        studentsCourseViewModel.restOfStudents.observe(viewLifecycleOwner, Observer {
            addStudentToCourseAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_student_to_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_restOfStudents.apply {
            layoutManager = addStudentToCourseViewManager
            adapter = addStudentToCourseAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddStudentToCourseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}