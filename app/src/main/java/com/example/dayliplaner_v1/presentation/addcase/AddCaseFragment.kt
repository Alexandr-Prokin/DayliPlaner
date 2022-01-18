package com.example.dayliplaner_v1.presentation.addcase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dayliplaner_v1.databinding.FragmentAddCaseBinding
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase
import io.realm.Realm

class AddCaseFragment : Fragment() {
    lateinit var binding: FragmentAddCaseBinding
    private lateinit var realm: Realm
//    private var dateTimeStart: DateTime = DateTime(0, 0, 0, 0, "00")
//    private var dateTimeFinish: DateTime = DateTime(0, 0, 0, 0, "00")
    private val convertTime = ConvertTimeStampUseCase()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCaseBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProviders.of(this)[AddCaseViewModel::class.java]
        binding.viewmodel = viewModel
        realm = Realm.getDefaultInstance()

        binding.calendarAddView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.setDay(year, month + 1, dayOfMonth)
        }

        binding.btnSave.setOnClickListener {
            viewModel.saveCase()
            // тосты ошибок
            // backStack
        }

        return binding.root
    }
}
