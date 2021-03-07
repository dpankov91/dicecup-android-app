    package com.example.dices.GUI

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.example.dices.R
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class   MainActivity : AppCompatActivity() {

    private val HISTORY_NAME = "history"

    private val TAG: String = "asd"
    val mGenerator = Random()

    val diceIds = arrayOf(0, R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4
                                                , R.drawable.d5, R.drawable.d6)

    val history = mutableListOf<Pair<String, List<Int>>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGoToHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("history", history.toTypedArray())
            startActivityForResult(intent, 5)
        }


        var numbers = arrayOf (1, 2, 3, 4, 5, 6)

        numbersSpinner.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, numbers)

        numbersSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position+1 === numbers[0]){
                    imgD1.visibility = View.VISIBLE
                    imgD2.visibility = View.INVISIBLE
                    imgD3.visibility = View.INVISIBLE
                    imgD4.visibility = View.INVISIBLE
                    imgD5.visibility = View.INVISIBLE
                    imgD6.visibility = View.INVISIBLE
                }
                if (position+1 === numbers[1]){
                    imgD1.visibility = View.VISIBLE
                    imgD2.visibility = View.VISIBLE
                    imgD3.visibility = View.INVISIBLE
                    imgD4.visibility = View.INVISIBLE
                    imgD5.visibility = View.INVISIBLE
                    imgD6.visibility = View.INVISIBLE
                }
                if (position+1 === numbers[2]){
                    imgD1.visibility = View.VISIBLE
                    imgD2.visibility = View.VISIBLE
                    imgD3.visibility = View.VISIBLE
                    imgD4.visibility = View.INVISIBLE
                    imgD5.visibility = View.INVISIBLE
                    imgD6.visibility = View.INVISIBLE
                }
                if (position+1 === numbers[3]){
                    imgD1.visibility = View.VISIBLE
                    imgD2.visibility = View.VISIBLE
                    imgD3.visibility = View.VISIBLE
                    imgD4.visibility = View.VISIBLE
                    imgD5.visibility = View.INVISIBLE
                    imgD6.visibility = View.INVISIBLE
                }
                if (position+1 === numbers[4]){
                    imgD1.visibility = View.VISIBLE
                    imgD2.visibility = View.VISIBLE
                    imgD3.visibility = View.VISIBLE
                    imgD4.visibility = View.VISIBLE
                    imgD5.visibility = View.VISIBLE
                    imgD6.visibility = View.INVISIBLE
                }
                if (position+1 === numbers[5]){
                    imgD1.visibility = View.VISIBLE
                    imgD2.visibility = View.VISIBLE
                    imgD3.visibility = View.VISIBLE
                    imgD4.visibility = View.VISIBLE
                    imgD5.visibility = View.VISIBLE
                    imgD6.visibility = View.VISIBLE
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickRoll(view: View) {
        val d1 = mGenerator.nextInt(6)+1
        val d2 = mGenerator.nextInt(6)+1
        val d3 = mGenerator.nextInt(6)+1
        val d4 = mGenerator.nextInt(6)+1
        val d5 = mGenerator.nextInt(6)+1
        val d6 = mGenerator.nextInt(6)+1

        imgD1.setImageResource(diceIds[d1])
        imgD2.setImageResource(diceIds[d2])
        imgD3.setImageResource(diceIds[d3])
        imgD4.setImageResource(diceIds[d4])
        imgD5.setImageResource(diceIds[d5])
        imgD6.setImageResource(diceIds[d6])

        if(history.size >= 5){
            history.removeAt(0)
        }

        when (numbersSpinner.selectedItemPosition) {
            0 -> history.add(Pair(getCurrentTime(), listOf(d1)))
            1 -> history.add(Pair(getCurrentTime(), listOf(d1, d2)))
            2 -> history.add(Pair(getCurrentTime(), listOf(d1, d2, d3)))
            3 -> history.add(Pair(getCurrentTime(), listOf(d1, d2, d3, d4)))
            4 -> history.add(Pair(getCurrentTime(), listOf(d1, d2, d3, d4, d5)))
            5 -> history.add(Pair(getCurrentTime(), listOf(d1, d2, d3, d4, d5, d6)))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime(): String {
        val now = LocalDateTime.now()
        var formatter = DateTimeFormatter
                .ofPattern("HH:mm:ss")
        return formatter.format(now)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "History Saved")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 5) {
            if (resultCode == 10)
                history.clear()
        }
    }
}
