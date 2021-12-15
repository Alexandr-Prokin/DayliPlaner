package com.example.dayliplaner_v1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dayliplaner_v1.databinding.ItemCaseRecordBinding

class CaseRecordAdapter: RecyclerView.Adapter<CaseRecordViewHolder>() {

    private var items = mutableListOf<CaseRecord>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseRecordViewHolder {
        return CaseRecordViewHolder(
            ItemCaseRecordBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: CaseRecordViewHolder, position: Int) {
       holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList(caseRecord: List<CaseRecord>, day : Int){
        items.clear()
        val newItem = caseRecord.filter { it.dateStart == day }
        items.addAll(newItem)
        notifyDataSetChanged()
    }
}