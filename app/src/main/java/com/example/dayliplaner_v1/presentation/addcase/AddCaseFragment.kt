package com.example.dayliplaner_v1.presentation.addcase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.dayliplaner_v1.databinding.FragmentAddCaseBinding

class AddCaseFragment : Fragment() {
    lateinit var binding: FragmentAddCaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCaseBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProviders.of(this)[AddCaseViewModel::class.java]
        binding.viewmodel = viewModel

        binding.calendarAddView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.setDay(year, month + 1, dayOfMonth)
        }

        binding.btnSave.setOnClickListener {
            context?.let { it -> viewModel.saveCase(it) }
            if (viewModel.backFragment) {
                this.findNavController().popBackStack()
            }
            // тосты ошибок
            // backStack
        }

        return binding.root
    }
}
