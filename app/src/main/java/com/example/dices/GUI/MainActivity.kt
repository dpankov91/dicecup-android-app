package com.example.dices

import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private val HISTORY_NAME = "history"

    private val TAG: String = "asd"
    val mGenerator = Random()

    val diceIds = arrayOf(0, R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4
                                                , R.drawable.d5, R.drawable.d6)

    val mHistory = mutableListOf<Pair<Int, Int>>()

    val hhistory = mutableListOf<Pair<String, List<Int>>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        //if(mHistory.size >= 5)
           // mHistory.removeAt(0)
        if(hhistory.size >= 5){
            hhistory.removeAt(0)
        }

        if(numbersSpinner.selectedItemPosition === 0 ) {
            val now = LocalDateTime.now()
            var formatter = DateTimeFormatter
                    .ofPattern("HH:mm:ss")
            hhistory.add(Pair(formatter.format(now), listOf(d1)))
        }
        if(numbersSpinner.selectedItemPosition === 1 ) {
            val now = LocalDateTime.now()
            var formatter = DateTimeFormatter
                    .ofPattern("HH:mm:ss")
            hhistory.add(Pair(formatter.format(now), listOf(d1, d2)))
        }
        if(numbersSpinner.selectedItemPosition === 2 ) {
            val now = LocalDateTime.now()
            var formatter = DateTimeFormatter
                    .ofPattern("HH:mm:ss")
            hhistory.add(Pair(formatter.format(now), listOf(d1, d2, d3)))
        }
        if(numbersSpinner.selectedItemPosition === 3 ) {
            val now = LocalDateTime.now()
            var formatter = DateTimeFormatter
                    .ofPattern("HH:mm:ss")
            hhistory.add(Pair(formatter.format(now), listOf(d1, d2, d3, d4)))
        }
        if(numbersSpinner.selectedItemPosition === 4 ) {
            val now = LocalDateTime.now()
            var formatter = DateTimeFormatter
                    .ofPattern("HH:mm:ss")
            hhistory.add(Pair(formatter.format(now), listOf(d1, d2, d3, d4, d5)))
        }
        if(numbersSpinner.selectedItemPosition === 5 ) {
            val now = LocalDateTime.now()
            var formatter = DateTimeFormatter
                    .ofPattern("HH:mm:ss")
            hhistory.add(Pair(formatter.format(now), listOf(d1, d2, d3, d4, d5, d6)))
        }
        //mHistory.add(Pair(d1, d2))
        updateHistory()
    }

    private fun updateHistory() {
        var s = " "
        //mHistory.forEach { p -> val e1 = p.first; val e2 = p.second
                                //s += "$e1 -$e2\n" }

        hhistory.forEach { p -> val e1 = p.first; val e2 = p.second
            s += "$e1 $e2\n" }

        tvHistory.text = s
    }

    fun clearHistory(view: View) {
        hhistory.clear()
        updateHistory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "History Saved")
    }
}
