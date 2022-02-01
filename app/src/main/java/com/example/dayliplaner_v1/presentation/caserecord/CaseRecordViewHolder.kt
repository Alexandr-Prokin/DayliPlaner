package com.example.dayliplaner_v1.presentation.caserecord

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.databinding.ItemCaseRecordBinding
import com.example.dayliplaner_v1.presentation.utils.DateFormatter

class CaseRecordViewHolder(private val binding: ItemCaseRecordBinding) : RecyclerView.ViewHolder(binding.root) {

    private val dateFormatter = DateFormatter()

    fun bind(
        day: CaseRecord,
        clickListener: (id: Int) -> Unit
    ) = with(binding) {
        textViewDataStart.text = dateFormatter.getTimeStandard(day.getDateStart())
        textViewDataFinish.text = dateFormatter.getTimeStandard(day.getDateFinish())
        textViewNameCase.text = day.getName()
        Log.e("Tag", "getTimeStandard=${dateFormatter.getTimeStandard(day.getDateFinish())}")
        root.setOnClickListener {
            clickListener(day.getId())
        }
    }
}
