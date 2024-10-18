package com.iegabrielamistral.mentalcare

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.iegabrielamistral.mentalcare.fragments.EjerciciosRelajacionFragment
import com.iegabrielamistral.mentalcare.fragments.HomeFragment
import com.iegabrielamistral.mentalcare.fragments.PerfilUsuarioFragment
import com.iegabrielamistral.mentalcare.fragments.TestFragment

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    lateinit var bnvView: BottomNavigationView

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
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.test -> {
                    loadFragment(TestFragment())
                    true
                }

                R.id.RELAJACION -> {
                    loadFragment(EjerciciosRelajacionFragment())
                    true
                }

                R.id.perfil_usuario -> {
                    loadFragment(PerfilUsuarioFragment())
                    true
                }

                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
        auth = Firebase.auth
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.cerrar_sesion, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_salir -> {
//                FirebaseAuth.getInstance().signOut()
//                val intent = Intent(this@MainActivity, MainActivity::class.java)
//
//                Toast.makeText(applicationContext, "Has cerrado sesión", Toast.LENGTH_SHORT).show()
//                startActivity(intent)
                return true

            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }
}
