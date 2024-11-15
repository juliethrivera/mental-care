package com.iegabrielamistral.mentalcare

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iegabrielamistral.mentalcare.fragments.EjerciciosRelajacionFragment
import com.iegabrielamistral.mentalcare.fragments.HomeFragment
import com.iegabrielamistral.mentalcare.fragments.PerfilUsuarioFragment
import com.iegabrielamistral.mentalcare.fragments.TestFragment

class MainActivity : AppCompatActivity() {

    // Declaración de la variable 'bnvView' de tipo BottomNavigationView, que se inicializará más abajo
    lateinit var bnvView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        // Carga el fragmento HomeFragment por defecto al inicio de la actividad
        loadFragment(HomeFragment())
        // Inicializa el BottomNavigationView con el ID 'bnvView'
        bnvView = findViewById(R.id.bnvView) as BottomNavigationView

        // Establece un listener para manejar la selección de elementos en el BottomNavigationView
        bnvView.setOnItemSelectedListener {
            // Dependiendo del ID del ítem seleccionado, carga el fragmento correspondiente
            when (it.itemId) {
                // Si se selecciona el ítem 'home', carga el fragmento HomeFragment
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                // Si se selecciona el ítem 'test', carga el fragmento TestFragment
                R.id.test -> {
                    loadFragment(TestFragment())
                    true
                }
                // Si se selecciona el ítem 'Relajación', carga el fragmento EjerciciosRelajacionFragment
                R.id.Relajación -> {
                    loadFragment(EjerciciosRelajacionFragment())
                    true
                }
                // Si se selecciona el ítem 'perfil_usuario', carga el fragmento PerfilUsuarioFragment
                R.id.perfil_usuario -> {
                    loadFragment(PerfilUsuarioFragment())
                    true
                }
                // Si no se selecciona ninguno de los ítems anteriores, carga el fragmento HomeFragment por defecto
                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }

    // Función privada para cargar un nuevo fragmento en el contenedor especificado
    private fun loadFragment(fragment: Fragment) {
        // Inicia una transacción de fragmentos con el FragmentManager de la actividad actual
        val transaction = supportFragmentManager.beginTransaction()

        // Reemplaza el fragmento actual en el contenedor con el nuevo fragmento proporcionado
        transaction.replace(R.id.fragmentContainerView, fragment)

        // Confirma la transacción para que se realice el cambio de fragmento
        transaction.commit()
    }
}
