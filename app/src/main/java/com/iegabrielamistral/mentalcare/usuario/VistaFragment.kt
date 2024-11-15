package com.iegabrielamistral.mentalcare.usuario

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.replace
import com.iegabrielamistral.mentalcare.R

class VistaFragment : Fragment() {

    companion object {
        fun newInstance() = VistaFragment()
    }

    private val viewModel: VistaViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.vista_fragment, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
        // Usamos un Handler con un delay de 2 segundos (2000 ms) para ejecutar una acción de reemplazo de fragmento
        Handler().postDelayed({
            // Verifica si el fragmento sigue agregado y si la actividad no está finalizando antes de realizar la transacción
            if (isAdded && requireActivity().isFinishing.not()) {
                // Crea una nueva instancia del fragmento 'RegistroFragment'
                val registroFragment = RegistroFragment()
                // Realiza una transacción de fragmento para reemplazar el fragmento actual con 'registroFragment'
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView2, registroFragment) // Reemplaza el fragmento en el contenedor con ID 'fragmentContainerView2'
                    .commit() // Realiza la transacción
            }
        }, 2000) // El código se ejecutará después de un retraso de 2000 milisegundos (2 segundos)


    }

}