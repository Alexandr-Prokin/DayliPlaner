package com.example.dayliplaner_v1.presentation.casedescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dayliplaner_v1.databinding.FragmentCaseDescriptionBinding

class CaseDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentCaseDescriptionBinding
    private val viewModel: CaseDescriptionViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseDescriptionBinding.inflate(inflater, container, false)
        val id = arguments?.getString("id_case")?.toInt()
        binding.viewmodel = viewModel
        viewModel.getList(id ?: 0)
        binding.materialButton.setOnClickListener {
            this.findNavController().popBackStack()
        }
        return binding.root
    }
}
