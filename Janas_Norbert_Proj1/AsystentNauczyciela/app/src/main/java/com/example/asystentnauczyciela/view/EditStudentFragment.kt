package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.viewmodel.AllStudentsViewModel
import kotlinx.android.synthetic.main.fragment_edit_student.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EditStudentFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var studentsViewModel: AllStudentsViewModel

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
        studentsViewModel = ViewModelProvider(requireActivity()).get(AllStudentsViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null){
            val currStudentId = EditStudentFragmentArgs.fromBundle(requireArguments()).currentStudentId
            editTextStudentNameEdit.setText(studentsViewModel.getStudentById(currStudentId)?.name ?: "None")
            editTextStudentLastNameEdit.setText(studentsViewModel.getStudentById(currStudentId)?.lastName ?: "None")

            buttonEditNewStudent.setOnClickListener {
                val newName = editTextStudentNameEdit.text.toString()
                val newLastName = editTextStudentLastNameEdit.text.toString()

                if (studentsViewModel.isNotEmptyOrBlank(newName) &&
                        studentsViewModel.isNotEmptyOrBlank(newLastName))
                {
                    studentsViewModel.updateStudent(currStudentId, newName, newLastName)
                    activity?.onBackPressed()
                } else
                    Toast.makeText(requireContext(), "Niepoprawne dane", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditStudentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}