package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.viewmodel.MarksOfStudentViewModel
import com.example.asystentnauczyciela.viewmodel.StudentMarkAdapter
import kotlinx.android.synthetic.main.fragment_marks_of_student.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MarksOfStudentFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var markOfStudentViewModel: MarksOfStudentViewModel
    private lateinit var markOfStudentAdapter: StudentMarkAdapter
    private lateinit var markOfStudentViewManager: RecyclerView.LayoutManager

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
        markOfStudentViewModel = ViewModelProvider(requireActivity()).get(MarksOfStudentViewModel::class.java)

        if(arguments != null){
            var studentId = MarksOfStudentFragmentArgs.fromBundle(requireArguments()).studentId
            var courseId = MarksOfStudentFragmentArgs.fromBundle(requireArguments()).courseId

            markOfStudentViewModel.initMarkStudentCourse(studentId, courseId)
            markOfStudentAdapter = StudentMarkAdapter(markOfStudentViewModel.markStudentCourse)
        }

        markOfStudentViewManager = LinearLayoutManager(requireContext())

        markOfStudentViewModel.markStudentCourse.observe(viewLifecycleOwner, Observer {
            markOfStudentAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marks_of_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_marksOfStudent.apply {
            layoutManager = markOfStudentViewManager
            adapter = markOfStudentAdapter
        }

        val avgObserver = Observer<String> {
            newAvg -> txtViewAverageMark.text = newAvg
        }

        if (arguments != null){
            var studentName = MarksOfStudentFragmentArgs.fromBundle(requireArguments()).studentName
            var courseName = MarksOfStudentFragmentArgs.fromBundle(requireArguments()).courseName

            markOfStudentViewModel.average.observe(viewLifecycleOwner, avgObserver)

            txtViewAverageMark.text = resources.getString(R.string.average_mark, markOfStudentViewModel.average)
            txtViewMarksStudentName.text = resources.getString(R.string.concatTitleForMarksStudentName, studentName)
            txtViewMarksCourseName.text = resources.getString(R.string.concatTitleForMarksCourseName, courseName)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MarksOfStudentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}