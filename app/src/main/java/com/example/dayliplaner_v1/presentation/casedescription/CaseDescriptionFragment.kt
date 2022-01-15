package com.example.dayliplaner_v1.presentation.casedescription

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dayliplaner_v1.data.CaseRecord
import com.example.dayliplaner_v1.databinding.FragmentCaseDescriptionBinding
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase
import io.realm.Realm

class CaseDescriptionFragment : Fragment() {
    lateinit var binding: FragmentCaseDescriptionBinding
    private var items = mutableListOf<CaseRecord>()
    private val idCase = 8
    lateinit var realm: Realm
    private var convertTime = ConvertTimeStampUseCase()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseDescriptionBinding.inflate(inflater, container, false)
        realm = Realm.getDefaultInstance()
        realm.where(CaseRecord::class.java)
            .equalTo("id", idCase)
            .findAll().let {
                viewGetDB(it)
            }
        binding.materialButton.setOnClickListener {

            val action = CaseDescriptionFragmentDirections
                .actionCaseDescriptionFragmentToCalendarFragment()
            this.findNavController().navigate(action)
        }

        return binding.root
    }

    private fun viewGetDB(caseRecord: List<CaseRecord>) {
        lateinit var timeStart: String
        lateinit var timeFinish: String
        for (i in caseRecord) {
            timeStart = convertTime.getTime(i.getDateStart())
            timeFinish = convertTime.getTime(i.getDateFinish())
            binding.nameTextView.text = i.getName()
            binding.descriptionTextView.text = i.getDescription()
            binding.timeTextView.text = "$timeStart-$timeFinish"
        }
    }
}
