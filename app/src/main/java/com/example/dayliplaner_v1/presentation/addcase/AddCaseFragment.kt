package com.example.dayliplaner_v1.presentation.addcase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

            if (viewModel.saveCase()) {
                this.findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Выберите дату", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}
