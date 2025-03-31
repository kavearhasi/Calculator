package com.example.calc

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import data.History

class HistoryAdapter(private val listener:ItemOnClick) : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {
    private  var oldHistory: List<History> = emptyList()

    inner class HistoryHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val history = LayoutInflater.from(parent.context).inflate(R.layout.event, parent, false)
        return HistoryHolder(history)
    }

    override fun getItemCount(): Int {
        return oldHistory.size
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.question).text = oldHistory[position].expression
            findViewById<TextView>(R.id.answer).text = oldHistory[position].result.toString()
            findViewById<TextView>(R.id.date).text = oldHistory[position].date

        }
        holder.itemView.findViewById<ConstraintLayout>(R.id.eventParent).setOnClickListener{
             val intent = Intent(holder.itemView.context, MainActivity::class.java)
            intent.putExtra("result",oldHistory[position].result.toString())
            holder.itemView.context.startActivity(intent)
        }
        holder.itemView.findViewById<ImageButton>(R.id.remove).setOnClickListener{
              listener.delete(oldHistory[position])
        }
    }

    fun setData(newData: List<History>) {
        val diffUtil = HistoryDiffUtil(oldHistory, newData)
        val calculation = DiffUtil.calculateDiff(diffUtil)
        oldHistory = newData
        calculation.dispatchUpdatesTo(this)
    }
}