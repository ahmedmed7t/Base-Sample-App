package com.example.baseappsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ex = (1..92).toList()
        TestRandom().generateRandomNumber(1, 100, ex)
    }
}