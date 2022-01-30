package com.example.dayliplaner_v1.presentation.caserecord

import androidx.recyclerview.widget.RecyclerView
import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.databinding.ItemCaseRecordBinding
import com.example.dayliplaner_v1.domain.usecase.GetTimeUseCase

class CaseRecordViewHolder(private val binding: ItemCaseRecordBinding) : RecyclerView.ViewHolder(binding.root) {

    private val getTime = GetTimeUseCase()

    fun bind(
        day: CaseRecord,
        clickListener: (id: Int) -> Unit
    ) = with(binding) {
        textViewDataStart.text = getTime.getTime(day.getDateStart())
        textViewDataFinish.text = getTime.getTime(day.getDateFinish())
        textViewNameCase.text = day.getDescription()
        root.setOnClickListener {
            clickListener(day.getId())
        }
    }
}
