package com.example.dayliplaner_v1

import androidx.recyclerview.widget.RecyclerView
import com.example.dayliplaner_v1.databinding.ItemCaseRecordBinding

class CaseRecordViewHolder(private val binding: ItemCaseRecordBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        day: CaseRecord,
        clickListener: (
            day: CaseRecord
        ) -> Unit
    ) = with(binding) {
        textViewDataStart.text = day.dateStart.toString()
        textViewDataFinish.text = day.dateFinish.toString()
        textViewNameCase.text = day.name
        root.setOnClickListener {
            clickListener(CaseRecord(day.id, day.dateStart, day.dateFinish, day.name, day.description))
        }
    }
}
