package com.example.dayliplaner_v1.presentation.caserecord

import androidx.recyclerview.widget.RecyclerView
import com.example.dayliplaner_v1.data.CaseRecord
import com.example.dayliplaner_v1.databinding.ItemCaseRecordBinding
import com.example.dayliplaner_v1.domain.usecase.ConvertTimeStampUseCase

class CaseRecordViewHolder(private val binding: ItemCaseRecordBinding) : RecyclerView.ViewHolder(binding.root) {

    private val convertTime = ConvertTimeStampUseCase()

    fun bind(
        day: CaseRecord,
        clickListener: (id: Int) -> Unit
    ) = with(binding) {
        textViewDataStart.text = convertTime.getTime(day.getDateStart())
        textViewDataFinish.text = convertTime.getTime(day.getDateFinish())
        textViewNameCase.text = day.getDescription()
        root.setOnClickListener {
            clickListener(day.getId())
        }
    }
}
