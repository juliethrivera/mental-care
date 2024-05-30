package com.iegabrielamistral.mentalcare

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.*
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
    fun onCreate(savedInstanceState: Bundle?) {
        val onCreate = super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

fun findViewById(main: Int): View {
    TODO("Not yet implemented")
}

fun setContentView(activityMain: Int) {
    TODO("Not yet implemented")
}

fun enableEdgeToEdge() {
    TODO("Not yet implemented")
}



