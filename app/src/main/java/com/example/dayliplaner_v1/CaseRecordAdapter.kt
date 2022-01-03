package com.example.dayliplaner_v1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dayliplaner_v1.databinding.ItemCaseRecordBinding

class CaseRecordAdapter(
    private val clickListener: (id: Int) -> Unit
) : RecyclerView.Adapter<CaseRecordViewHolder>() {

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

    fun submitList(caseRecord: List<CaseRecord>, day: Int) {
        items.clear()
        val newItemDay = caseRecord.filter { it.getId() == day }
        items.addAll(newItemDay)
        notifyDataSetChanged()
    }
//    fun caseDescriptionList(caseRecord: List<CaseRecord>, id: Int) {
//        items.clear()
//        val newItemId = caseRecord.filter { it.id == id }
//        items.addAll(newItemId)
//        notifyDataSetChanged()
//    }
}
