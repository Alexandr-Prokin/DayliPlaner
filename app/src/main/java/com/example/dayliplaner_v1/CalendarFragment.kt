package com.example.dayliplaner_v1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.dayliplaner_v1.databinding.FragmentCalendarBinding
import androidx.navigation.fragment.findNavController

class CalendarFragment : Fragment() {
    lateinit var binding: FragmentCalendarBinding
    @RequiresApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)

        val caseRecord = listOf(
            CaseRecord(
                id = 1,
                dateStart = 2,
                dateFinish = 3,
                name = "Case one",
                description = "Выполнить задание 1"
            ),
            CaseRecord(
                id = 2,
                dateStart = 5,
                dateFinish = 6,
                name = "Case two",
                description = "Выполнить задание 1"
            ),
            CaseRecord(
                id = 3,
                dateStart = 7,
                dateFinish = 9,
                name = "Case tree",
                description = "Выполнить задание 1"
            ),
        )
        val adapter = CaseRecordAdapter{ id ->
            Toast.makeText(context, "$id", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerViewCalendar.adapter = adapter
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            adapter.submitList(caseRecord, dayOfMonth)
        }

        return binding.root
    }
}
