package com.example.dayliplaner_v1.presentation.casedescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders.*
import androidx.navigation.fragment.findNavController
import com.example.dayliplaner_v1.databinding.FragmentCaseDescriptionBinding
import io.realm.Realm

class CaseDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentCaseDescriptionBinding

    lateinit var realm: Realm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseDescriptionBinding.inflate(inflater, container, false)
        val viewModel = of(this)[CaseDescriptionViewModel::class.java]
        val _id = arguments?.getString("id_case")?.toInt()!!

        binding.viewmodel = viewModel
        viewModel.getList(_id)
        binding.materialButton.setOnClickListener {

            val action = CaseDescriptionFragmentDirections
                .actionCaseDescriptionFragmentToCalendarFragment()
            this.findNavController().navigate(action)
        }

        return binding.root
    }
}