package com.example.dayliplaner_v1.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dayliplaner_v1.CaseRecord
import com.example.dayliplaner_v1.databinding.FragmentAddCaseBinding
import com.example.dayliplaner_v1.domain.models.DateTime
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase
import io.realm.Realm
import io.realm.exceptions.RealmException

class AddCaseFragment : Fragment() {
    lateinit var binding: FragmentAddCaseBinding
    private lateinit var realm: Realm
    private var dateTimeStart: DateTime = DateTime(0, 0, 0, "00", "00")
    private var dateTimeFinish: DateTime = DateTime(0, 0, 0, "00", "00")
    private val convertTime = ConvertTimeStampUseCase()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCaseBinding.inflate(inflater, container, false)
        realm = Realm.getDefaultInstance()
        // val id = CaseDescriptionFragmentArgs.fromBundle(requireArguments()).id

        binding.calendarAddView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            dateTimeStart.month = month + 1
            dateTimeStart.year = year
            dateTimeStart.day = dayOfMonth
            dateTimeFinish.month = month + 1
            dateTimeFinish.year = year
            dateTimeFinish.day = dayOfMonth
        }

        binding.btnSave.setOnClickListener {
            realm.beginTransaction()
            var count = 0
            realm.where(CaseRecord::class.java).findAll().let {
                for (i in it) {
                    count++
                }
            }
            try {
                var caseRecord = realm.createObject(CaseRecord::class.java)
                dateTimeStart.hours = binding.timeStartAddTextView.text.toString()
                dateTimeFinish.hours = binding.timeFinishAddTextView.text.toString()
                caseRecord.setId(count + 1)
                caseRecord.setName(binding.NameInputEditText.text.toString())
                caseRecord.setDescription(binding.descriptionAddTextView.text.toString())
                caseRecord.setDateStart(convertTime.setTimeStamp(dateTimeStart))
                caseRecord.setDateFinish(convertTime.setTimeStamp(dateTimeFinish))

                realm.commitTransaction()
            } catch (e: RealmException) {
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
            }
            val action = AddCaseFragmentDirections
                .actionAddCaseFragmentToCalendarFragment()
            this.findNavController().navigate(action)
        }

        return binding.root
    }
}
