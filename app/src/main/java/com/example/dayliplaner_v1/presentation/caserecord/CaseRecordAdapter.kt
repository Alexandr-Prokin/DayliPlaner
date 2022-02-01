package com.example.dayliplaner_v1.presentation.caserecord

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dayliplaner_v1.data.models.CaseRecord
import com.example.dayliplaner_v1.databinding.ItemCaseRecordBinding
import com.example.dayliplaner_v1.presentation.utils.DateFormatter

class CaseRecordAdapter(
    private val clickListener: (id: Int) -> Unit
) : RecyclerView.Adapter<CaseRecordViewHolder>() {

    private var items = mutableListOf<CaseRecord>()
    private val dateFormatter = DateFormatter()

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
            dateFormatter.getDate(it.getDateStart()) == "$day-${month + 1}-$year"
        }
        items.addAll(newItemDay.sortedBy { it.getDateStart() })
        notifyDataSetChanged()
    }
}
