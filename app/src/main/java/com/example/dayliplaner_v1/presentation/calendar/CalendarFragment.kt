package com.example.dayliplaner_v1.presentation.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dayliplaner_v1.data.CaseRecord
import com.example.dayliplaner_v1.databinding.FragmentCalendarBinding
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase
import com.example.dayliplaner_v1.presentation.caserecord.CaseRecordAdapter
import io.realm.Realm

class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    lateinit var realm: Realm
    private val convertDate = ConvertTimeStampUseCase()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)

        val adapter = CaseRecordAdapter { id ->
            val action =
                CalendarFragmentDirections.actionCalendarFragmentToCaseDescriptionFragment()
            this.findNavController().navigate(action)
        }

        binding.recyclerViewCalendar.adapter = adapter

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            realm = Realm.getDefaultInstance()
            realm.where(CaseRecord::class.java).findAll().let {
                adapter.submitList(it, year, month, dayOfMonth)
            }
        }
        binding.addButton.setOnClickListener {
            val action =
                CalendarFragmentDirections.actionCalendarFragmentToAddCaseFragment()
            this.findNavController().navigate(action)
        }
        return binding.root
    }
}
