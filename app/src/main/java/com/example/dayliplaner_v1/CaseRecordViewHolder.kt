package com.example.dayliplaner_v1

import androidx.recyclerview.widget.RecyclerView
import com.example.dayliplaner_v1.databinding.ItemCaseRecordBinding

class CaseRecordViewHolder(private val binding: ItemCaseRecordBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        day: CaseRecord,
        clickListener: (id: Int) -> Unit
    ) = with(binding) {
        textViewDataStart.text = day.getDateStart()
        textViewDataFinish.text = day.getDateFinish()
        textViewNameCase.text = day.getDescription()
        root.setOnClickListener {
            clickListener(day.getId())
        }
    }
}
