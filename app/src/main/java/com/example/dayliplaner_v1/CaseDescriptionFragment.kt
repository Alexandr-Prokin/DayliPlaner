package com.example.dayliplaner_v1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dayliplaner_v1.databinding.FragmentCaseDescriptionBinding

class CaseDescriptionFragment : Fragment() {
    lateinit var binding: FragmentCaseDescriptionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseDescriptionBinding.inflate(inflater, container, false)
        val name = CaseDescriptionFragmentArgs.fromBundle(requireArguments()).day
        binding.nameTextView.text = name.toString()
        return binding.root
    }
}
