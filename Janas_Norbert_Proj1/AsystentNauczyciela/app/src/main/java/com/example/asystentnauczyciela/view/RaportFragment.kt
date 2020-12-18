package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.viewmodel.RaportAdapter
import com.example.asystentnauczyciela.viewmodel.RaportViewModel
import kotlinx.android.synthetic.main.fragment_raport.*
import java.text.DateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RaportFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var raportViewModel: RaportViewModel
    private lateinit var raportAdapter: RaportAdapter
    private lateinit var raportViewManager: RecyclerView.LayoutManager

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
        raportViewModel = ViewModelProvider(requireActivity()).get(RaportViewModel::class.java)

        raportViewModel.initMarksRaport()

        raportAdapter = RaportAdapter(raportViewModel.marksRaport)

        raportViewManager = LinearLayoutManager(requireContext())

        raportViewModel.marksRaport.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            raportAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_raport, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_RaportMarks.apply {
            adapter = raportAdapter
            layoutManager = raportViewManager
        }

        txtViewRaportDate.text = resources.getString(
            R.string.concatTitleRaport,
            DateFormat.getDateInstance(DateFormat.SHORT).format(Calendar.getInstance().time)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RaportFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}