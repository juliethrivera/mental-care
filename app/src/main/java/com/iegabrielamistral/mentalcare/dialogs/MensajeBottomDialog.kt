package com.iegabrielamistral.mentalcare.dialogs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.findFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.fragments.EJERCICIO
import com.iegabrielamistral.mentalcare.fragments.MEDITACION
import com.iegabrielamistral.mentalcare.fragments.RESPIRACION_PROFUNDA
import com.iegabrielamistral.mentalcare.fragments.RespiracionProfundaFragment
import com.iegabrielamistral.mentalcare.fragments.TIPO_EJERCICIO
import com.iegabrielamistral.mentalcare.fragments.VistaEjercicioFragment
import com.iegabrielamistral.mentalcare.fragments.YOGA
import org.checkerframework.common.subtyping.qual.Bottom

class MensajeBottomDialog : BottomSheetDialogFragment() {

    lateinit var botonEje: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mensaje, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        botonEje = view.findViewById(R.id.button3)

        val mensaje = arguments?.getString(MENSAJE)

        val tipo = arguments?.getInt(MOOD)

        Log.d("MensajeBottomDialog", mensaje!!)

        val mensajeText: TextView = view.findViewById(R.id.mensaje_text)

        mensajeText.text = mensaje!!

        botonEje.setOnClickListener {
            when (tipo) {

                0 -> {
                    // Se crea un objeto Bundle para pasar datos entre fragmentos.
                    val bundle: Bundle = Bundle()
                    // Se agregan dos datos al Bundle:
                    // Una cadena que indica el tipo de ejercicio (en este caso, "RESPIRACION_PROFUNDA").
                    // Un valor booleano indicando que el ejercicio está activo (true).
                    bundle.putString(TIPO_EJERCICIO, RESPIRACION_PROFUNDA)
                    bundle.putBoolean(EJERCICIO, true)
                    // Se crea una nueva instancia del fragmento VistaEjercicioFragment.
                    val vistaEjercicioFragment = VistaEjercicioFragment()
                    // Se asignan los datos (bundle) al fragmento como argumentos.
                    vistaEjercicioFragment.arguments = bundle
                    // Inicia una transacción para reemplazar el fragmento actual por el nuevo fragmento (vistaEjercicioFragment).
                    // El fragmento reemplazado se muestra dentro de un contenedor con el ID R.id.fragmentContainerView.
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, vistaEjercicioFragment)
                        .commit()
                }

                1 -> {
                    val bundle: Bundle = Bundle()
                    bundle.putString(TIPO_EJERCICIO, RESPIRACION_PROFUNDA)
                    bundle.putBoolean(EJERCICIO, true)
                    val vistaEjercicioFragment = VistaEjercicioFragment()
                    vistaEjercicioFragment.arguments = bundle

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, vistaEjercicioFragment).commit()
                }

                2 -> {
                    val bundle: Bundle = Bundle()
                    bundle.putString(TIPO_EJERCICIO, RESPIRACION_PROFUNDA)
                    bundle.putBoolean(EJERCICIO, true)
                    val vistaEjercicioFragment = VistaEjercicioFragment()
                    vistaEjercicioFragment.arguments = bundle

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, vistaEjercicioFragment).commit()
                }

                3 -> {
                    val bundle: Bundle = Bundle()
                    bundle.putString(TIPO_EJERCICIO, MEDITACION)
                    bundle.putBoolean(EJERCICIO, true)
                    val vistaEjercicioFragment = VistaEjercicioFragment()
                    vistaEjercicioFragment.arguments = bundle

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, vistaEjercicioFragment).commit()
                }

                4 -> {
                    val bundle: Bundle = Bundle()
                    bundle.putString(TIPO_EJERCICIO, YOGA)
                    bundle.putBoolean(EJERCICIO, true)
                    val vistaEjercicioFragment = VistaEjercicioFragment()
                    vistaEjercicioFragment.arguments = bundle

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, vistaEjercicioFragment).commit()
                }
            }
        }
    }
}

const val MENSAJE = "dialog_message"
const val MOOD = "mood_tipo"