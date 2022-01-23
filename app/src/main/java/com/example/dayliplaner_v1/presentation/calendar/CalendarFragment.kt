package com.example.dayliplaner_v1.presentation.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.dayliplaner_v1.R
import com.example.dayliplaner_v1.databinding.FragmentCalendarBinding
import com.example.dayliplaner_v1.presentation.caserecord.CaseRecordAdapter

class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    private val bundle = Bundle()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProviders.of(this)[CalendarViewModel::class.java]

        val adapter = CaseRecordAdapter { id ->
            bundle.putString("id_case", id.toString())
            this.findNavController().navigate(R.id.caseDescriptionFragment, bundle)
        }

        binding.recyclerViewCalendar.adapter = adapter

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            adapter.submitList(viewModel.getData(), year, month, dayOfMonth)
        }
        binding.addButton.setOnClickListener {

           // this.findNavController().navigate(R.id.action_calendarFragment_to_addCaseFragment, bundle)
            this.findNavController().navigate(R.id.action_calendarFragment_to_addCaseScreen , bundle)
        }
        return binding.root
    }
}
