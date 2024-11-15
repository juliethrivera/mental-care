package com.iegabrielamistral.mentalcare.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.findFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iegabrielamistral.mentalcare.R

class MensajeBottomDialog: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mensaje, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* Obtiene el valor del mensaje desde los argumentos del
        fragmento usando la clave definida por la constante MENSAJE*/
        val mensaje = arguments?.getString(MENSAJE)

        // Registra el mensaje en los logs para depuración (Logcat) con una etiqueta específica
        Log.d("MensajeBottomDialog", mensaje!!)

        // Encuentra la vista TextView donde se mostrará el mensaje
        val mensajeText: TextView = view.findViewById(R.id.mensaje_text)

        // Asigna el valor del mensaje al TextView para que se muestre en la interfaz de usuario
        mensajeText.text = mensaje!!


    }

}
/*Constante que representa el identificador del mensaje que se mostrará en el diálogo*/
const val MENSAJE = "dialog_message"