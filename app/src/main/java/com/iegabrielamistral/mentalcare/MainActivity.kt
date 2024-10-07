package com.iegabrielamistral.mentalcare

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iegabrielamistral.mentalcare.fragments.EjerciciosRelajacionFragment
import com.iegabrielamistral.mentalcare.fragments.HomeFragment

import com.iegabrielamistral.mentalcare.fragments.PerfilUsuarioFragment
import com.iegabrielamistral.mentalcare.fragments.RelajacionFragment
import com.iegabrielamistral.mentalcare.fragments.TestFragment
import com.iegabrielamistral.mentalcare.fragments.TestMentalBlankFragment

class MainActivity : AppCompatActivity() {

    lateinit var bnvView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       loadFragment(HomeFragment())
        bnvView = findViewById(R.id.bnvView) as BottomNavigationView
        bnvView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.home ->{
                    loadFragment(HomeFragment())
                    true
                }
                R.id.test -> {
                    loadFragment(TestFragment())
                    true
                }
                R.id.Relajación ->{
                    loadFragment(EjerciciosRelajacionFragment())
                    true
                }
                R.id.perfil_usuario ->{
                    loadFragment(PerfilUsuarioFragment())
                    true
                }
                else ->{
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fragment)
        transaction.commit()
    }
}
