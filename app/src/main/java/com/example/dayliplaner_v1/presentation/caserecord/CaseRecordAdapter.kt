package com.example.dayliplaner_v1.presentation.caserecord

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.databinding.ItemCaseRecordBinding
import com.example.dayliplaner_v1.domain.usecase.GetDayMonthYearUseCase

class CaseRecordAdapter(
    private val clickListener: (id: Int) -> Unit
) : RecyclerView.Adapter<CaseRecordViewHolder>() {

    private val getDate = GetDayMonthYearUseCase()
    private var items = mutableListOf<CaseRecord>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseRecordViewHolder {
        return CaseRecordViewHolder(
            ItemCaseRecordBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CaseRecordViewHolder, position: Int) {
        holder.bind(
            items[position],
            clickListener
        )
    }

    override fun getItemCount() = items.size

    fun submitList(caseRecord: List<CaseRecord>, year: Int, month: Int, day: Int) {
        items.clear()
        val newItemDay = caseRecord.filter {
            getDate.getDayMonthYear(it.getDateStart(), year, month + 1, day)
        }
        items.addAll(newItemDay.sortedBy { it.getDateStart() })
        notifyDataSetChanged()
    }
}
