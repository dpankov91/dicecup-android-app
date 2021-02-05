package com.example.dices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val mGenerator = Random()

    val diceIds = arrayOf(0, R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4
                                                , R.drawable.d5, R.drawable.d6)

    val mHistory = mutableListOf<Pair<Int, Int>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickRoll(view: View) {
        val d1 = mGenerator.nextInt(6)+1
        val d2 = mGenerator.nextInt(6)+1

        imgD1.setImageResource(diceIds[d1])
        imgD2.setImageResource(diceIds[d2])

        if(mHistory.size >= 5)
            mHistory.removeAt(0)
        mHistory.add(Pair(d1, d2))
        updateHistory()
    }

    private fun updateHistory() {
        var s = " "
        mHistory.forEach { p -> val e1 = p.first; val e2 = p.second
                                s += "$e1 -$e2\n" }
        tvHistory.text = s
    }

    fun clearHistory(view: View) {
        mHistory.clear()
        updateHistory()
    }
}