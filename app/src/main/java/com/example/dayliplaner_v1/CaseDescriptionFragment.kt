package com.example.dayliplaner_v1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dayliplaner_v1.databinding.FragmentCaseDescriptionBinding

class CaseDescriptionFragment : Fragment() {
    lateinit var binding: FragmentCaseDescriptionBinding
    private var items = mutableListOf<CaseRecord>()
    @RequiresApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseDescriptionBinding.inflate(inflater, container, false)

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
        val id = CaseDescriptionFragmentArgs.fromBundle(requireArguments()).id
        val _caseRecord = caseRecord.filter { it.id == id }
        _caseRecord.forEach {
            binding.nameTextView.text = it.name
            binding.timeTextView.text = "${it.dateStart}-${it.dateFinish}"
            binding.descriptionTextView.text = it.description
        }
        binding.materialButton.setOnClickListener {

            val action = CaseDescriptionFragmentDirections
                .actionCaseDescriptionFragmentToCalendarFragment(id)
            this.findNavController().navigate(action)
        }

        return binding.root
    }
}
