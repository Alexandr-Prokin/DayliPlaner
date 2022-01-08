package com.example.dayliplaner_v1.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dayliplaner_v1.CaseRecord
import com.example.dayliplaner_v1.CaseRecordAdapter
import com.example.dayliplaner_v1.databinding.FragmentCalendarBinding
import io.realm.Realm

class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    lateinit var caseRecordAdapter: CaseRecordAdapter
    lateinit var realm: Realm
    lateinit var caseList: ArrayList<CaseRecord>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)

        val adapter = CaseRecordAdapter { id ->
            val action = CalendarFragmentDirections.actionCalendarFragmentToCaseDescriptionFragment()
            this.findNavController().navigate(action)
        }

        binding.recyclerViewCalendar.adapter = adapter

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            realm = Realm.getDefaultInstance()
            realm.where(CaseRecord::class.java).findAll().let {
                adapter.submitList(it, dayOfMonth)
            }
        }
        binding.addButton.setOnClickListener {
            val action = CalendarFragmentDirections
                .actionCalendarFragmentToAddCaseFragment()
            this.findNavController().navigate(action)
        }
        return binding.root
    }
}
