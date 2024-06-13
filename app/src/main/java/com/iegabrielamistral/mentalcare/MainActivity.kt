
package com.iegabrielamistral.mentalcare

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.*
import androidx.core.view.WindowInsetsCompat
import com.iegabrielamistral.mentalcare.fragments.EjerciciosRespiracionFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }
}


