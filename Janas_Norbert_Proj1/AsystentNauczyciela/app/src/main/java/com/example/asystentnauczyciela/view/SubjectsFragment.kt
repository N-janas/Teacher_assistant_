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
import com.example.asystentnauczyciela.viewmodel.SubjectsListAdapter
import com.example.asystentnauczyciela.viewmodel.SubjectsViewModel
import kotlinx.android.synthetic.main.fragment_subjects.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SubjectsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var subjectViewModel: SubjectsViewModel
    private lateinit var subjectsAdapter: SubjectsListAdapter
    private lateinit var subjectsViewManager: RecyclerView.LayoutManager


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
        // Initializing ViewModel for Subjects
        subjectViewModel = ViewModelProvider(requireActivity()).get(SubjectsViewModel::class.java)

        subjectsViewManager = LinearLayoutManager(requireContext())
        subjectsAdapter = SubjectsListAdapter(subjectViewModel.subjects)
        subjectsAdapter.setDatabaseDelListener(subjectViewModel)

        subjectViewModel.subjects.observe(viewLifecycleOwner, Observer {
            subjectsAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subjects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Apply Adapter and Layout
        recyclerView_subjects.apply {
            adapter = subjectsAdapter
            layoutManager = subjectsViewManager
        }

        // Add new course
        buttonAddCourse.setOnClickListener {
            if (subjectViewModel.isNotEmptyOrBlankOrExists(editTextCourseName.text.toString())){
                subjectViewModel.addCourse(editTextCourseName.text.toString())
                editTextCourseName.text.clear()
            } else
                Toast.makeText(requireContext(), "Niepoprawna nazwa", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubjectsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}