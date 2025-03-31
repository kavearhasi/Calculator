package com.example.calc

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calc.databinding.ActivityHistoryBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import data.History
import data.HistoryViewModel


class HistoryActivity : AppCompatActivity(), ItemOnClick {
    private lateinit var binding:ActivityHistoryBinding
    private lateinit var adapter: HistoryAdapter
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(binding.root)
        init()

        //observe the and reflect the changes in history
        getAllHistory()

        // When the back button is pressed, the current activity is finished.
        val back = findViewById<ImageButton>(R.id.back)
        back.setOnClickListener { finish() }

        // on pressing delete,deletes entire history
        val delete = findViewById<ImageButton>(R.id.delete)
        delete.setOnClickListener {
            if (adapter.itemCount != 0) {
                cleanHistory()
            } else {
                Toast.makeText(this, "No History", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun init() {
        setupAdapter()
        setupViewModel()

    }

    private fun setupAdapter() {
        adapter = HistoryAdapter(this)
        val recyclerView: RecyclerView = findViewById(R.id.events)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupViewModel() {
        historyViewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
    }

    private fun getAllHistory() {
        historyViewModel.readAllHistory.observe(this, Observer { history ->
            if ( history.isEmpty()){
          binding.delete?.visibility= View.GONE
            }
            else{
                binding.delete?.visibility= View.VISIBLE
            }
            adapter.setData(history)

        })
    }

    private fun cleanHistory() {
        val builder = MaterialAlertDialogBuilder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            historyViewModel.deleteAll()
            Toast.makeText(this, "History cleaned", Toast.LENGTH_SHORT).show()
        }
        builder.setNeutralButton("No") { _, _ -> }
        builder.setTitle("Clean History")
        builder.setMessage("Are you sure you want clear the entire history")
        builder.create().show()
    }

    override fun delete(item: History) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Remove History")
            .setMessage("Do you want to remove this history ${item.result}")
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes") { _, _ ->
                historyViewModel.delete(item)
                Toast.makeText(this, "History Removed", Toast.LENGTH_SHORT).show()
            }
            .show()

    }


}