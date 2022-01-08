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
import io.realm.Realm
import io.realm.exceptions.RealmException

class AddCaseFragment : Fragment() {
    lateinit var binding: FragmentAddCaseBinding
    private lateinit var caseRecord: ArrayList<CaseRecord>
    private lateinit var realm: Realm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCaseBinding.inflate(inflater, container, false)
        realm = Realm.getDefaultInstance()
        // val id = CaseDescriptionFragmentArgs.fromBundle(requireArguments()).id

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
                caseRecord.setId(count + 1)
                caseRecord.setName(binding.NameInputEditText.text.toString())
                caseRecord.setDescription(binding.descriptionAddTextView.text.toString())
                caseRecord.setDateStart(binding.timeStartAddTextView.text.toString())
                caseRecord.setDateStart(binding.timeFinishAddTextView.text.toString())
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
