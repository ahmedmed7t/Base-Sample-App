package com.example.baseappsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ex = (0..98).toList()
        val excludeArray = mutableListOf<Int>()

        val end = 99
        for (index in 1..100) {
            val randomNumber = TestRandom().nextIntInRangeButExclude(0, end, excludeArray)
        }

        val x = TestRandom().numberPadding("111", 2, '0');

        Log.v("Medhat  ", x)
    }
}