package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.viewmodel.SubjectsViewModel
import kotlinx.android.synthetic.main.fragment_edit_course.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditCourseFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var subjectViewModel: SubjectsViewModel

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

        subjectViewModel = ViewModelProvider(requireActivity()).get(SubjectsViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get current Course and set edit text with its name
        if (arguments != null){
            val currentCourseId = EditCourseFragmentArgs.fromBundle(requireArguments()).currentCourseId
            var currentName = subjectViewModel.getCourseById(currentCourseId)?.name ?: "None"
            editTextCourseNewName.setText(currentName)

            // If edit is clicked check if new name exists or new name is blank and if it is then make toast
            buttonEditCourse.setOnClickListener {
                val newName = editTextCourseNewName.text.toString()

                // Let update when is not Emplty/Blank/Exists or no changes were applied to name
                if (subjectViewModel.isNotEmptyOrBlankOrExists(newName) || newName == currentName){
                    subjectViewModel.updateCourse(currentCourseId, newName)
                    activity?.onBackPressed()
                } else
                    Toast.makeText(requireContext(), "Niepoprawna nazwa", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditCourseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}