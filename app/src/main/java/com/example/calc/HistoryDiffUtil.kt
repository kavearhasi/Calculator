package com.example.calc

import androidx.recyclerview.widget.DiffUtil
import data.History

class HistoryDiffUtil(
    private val oldData: List<History>,
    private val newData: List<History>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].id == newData[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldData[oldItemPosition].expression == newData[newItemPosition].expression) &&
                oldData[oldItemPosition].result == newData[newItemPosition].result
    }

}
