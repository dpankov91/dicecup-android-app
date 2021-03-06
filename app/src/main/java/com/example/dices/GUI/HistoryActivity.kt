package com.example.dices.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.example.dices.R

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val history = intent.extras?.getSerializable("history") as Array<Pair<String, List<Int>>>

        val adapter: ListAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, history)

    }
}