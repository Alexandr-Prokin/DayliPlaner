package com.example.dayliplaner_v1.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dayliplaner_v1.CaseRecord
import com.example.dayliplaner_v1.databinding.FragmentCaseDescriptionBinding

class CaseDescriptionFragment : Fragment() {
    lateinit var binding: FragmentCaseDescriptionBinding
    private var items = mutableListOf<CaseRecord>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseDescriptionBinding.inflate(inflater, container, false)

//        val _caseRecord = caseRecord.filter { it.id == id }
//        _caseRecord.forEach {
//            binding.nameTextView.text = it.name
//            binding.timeTextView.text = "${it.dateStart}-${it.dateFinish}"
//            binding.descriptionTextView.text = it.description
//        }
        binding.materialButton.setOnClickListener {

            val action = CaseDescriptionFragmentDirections
                .actionCaseDescriptionFragmentToCalendarFragment()
            this.findNavController().navigate(action)
        }

        return binding.root
    }
}
