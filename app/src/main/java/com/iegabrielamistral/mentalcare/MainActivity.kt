@file:Suppress("UNREACHABLE_CODE")

package com.iegabrielamistral.mentalcare

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.*
import androidx.core.view.WindowInsetsCompat
import com.iegabrielamistral.mentalcare.fragments.EjerciciosRespiracionFragment

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


fun setContentView(: Int) {
    TODO("Not yet implemented")
}

fun enableEdgeToEdge() {
    TODO("Not yet implemented")
}




