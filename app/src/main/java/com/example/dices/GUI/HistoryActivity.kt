package com.example.dices.GUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.example.dices.R
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        if (intent.extras?.getSerializable("history") != null) {
            val history = intent.extras?.getSerializable("history") as Array<Pair<String, List<Int>>>

            val adapter: ListAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1, history)

            lvHistory.adapter = adapter

            btnClearHistory.setOnClickListener {
                setResult(10)
                //finish()
            }

             btnBack.setOnClickListener {
                 val intent = Intent(this, MainActivity::class.java)
                 startActivity(intent)
            }
        }
    }
}