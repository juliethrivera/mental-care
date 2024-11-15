package com.iegabrielamistral.mentalcare.usuario

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.MainActivity
import com.iegabrielamistral.mentalcare.R

class RestablecerFragment : Fragment() {
    //aqui estoy creando una variable a los componentes del layout
    lateinit var ediTxtEmail: EditText
    lateinit var btnResetPassaword: Button
    lateinit var regresar: Button

    // estoy declarando FirebaseAuth
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_restablecer, container, false)
    }

    companion object {
        fun newInstance() = RestablecerFragment()
    }


    private val viewModel: RestablecerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //aqui estoy inicializando los componentes del layout
        ediTxtEmail = view.findViewById(R.id.ediTxtEmail)
        btnResetPassaword = view.findViewById(R.id.btnResetPassaword)
        regresar = view.findViewById(R.id.back_regresar)/* Deshabilita el botón de restablecimiento
        de contraseña y reduce su opacidad para indicar que está inactivo  */
        btnResetPassaword.isEnabled = false  // Deshabilita la interacción con el botón
        btnResetPassaword.alpha =
            0.5f   // Reduce la opacidad del botón para darle una apariencia desactivada

        /* Inicializa la instancia de FirebaseAuth para gestionar la autenticación de usuarios*/
        auth = Firebase.auth

        /* Establece un listener para el botón "regresar" que reemplaza
         el fragmento actual con un nuevo fragmento
         */
        regresar.setOnClickListener {
            // Crea una nueva instancia del fragmento VistaFragment
            val vistaFragment = VistaFragment()
            // Realiza la transacción de fragmentos: reemplaza el fragmento actual con el nuevo VistaFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, vistaFragment)
                .commit()// Ejecuta la transacción

        }

        // Crea un TextWatcher para monitorear los cambios en el campo de texto (ediTxtEmail) y habilitar el botón de restablecimiento de contraseña
        val textWatcher = object : TextWatcher {
            // Este método se ejecuta antes de que el texto cambie (en este caso, no se usa)
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // No se realiza ninguna acción antes de que el texto cambie
            }

            // Este método se ejecuta cuando el texto cambia
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Habilita el botón de restablecimiento de contraseña solo si el campo de correo electrónico no está vacío
                btnResetPassaword.isEnabled = (ediTxtEmail.text!!.isNotEmpty())

                // Establece la opacidad del botón a 1 (completamente visible) cuando se introduce texto
                btnResetPassaword.alpha = 1f
            }

            // Este método se ejecuta después de que el texto haya cambiado (en este caso, no se usa)
            override fun afterTextChanged(p0: Editable?) {
                // No se realiza ninguna acción después de que el texto haya cambiado
            }
        }
        // Añade el TextWatcher al campo de texto ediTxtEmail para monitorizar los cambios en el texto ingresado
        ediTxtEmail.addTextChangedListener(textWatcher)
        // Aquí estoy llamando la función `passwordRecover` de la actividad LoginActivity
        // cuando el botón de restablecimiento de contraseña es presionado.

        btnResetPassaword.setOnClickListener {
            // Castea la actividad actual (requireActivity()) a LoginActivity y llama a la función passwordRecover
            (requireActivity() as LoginActivity).apply {
                passwordRecover(ediTxtEmail.text.toString())  // Pasa el texto ingresado en ediTxtEmail como argumento
            }
        }
    }

}