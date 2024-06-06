package com.iegabrielamistral.mentalcare.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

        val mensaje = arguments?.getString(MENSAJE)

        Log.d("MensajeBottomDialog", mensaje!!)

        val mensajeText: TextView = view.findViewById(R.id.mensaje_text)

        mensajeText.text = mensaje!!

    }

}

const val MENSAJE = "dialog_message"