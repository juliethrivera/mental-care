package com.iegabrielamistral.mentalcare.usuario

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.MainActivity
import com.iegabrielamistral.mentalcare.R

class InicioFragment : Fragment() {
    //lateinit var password : TextView

    companion object {
        fun newInstance() = InicioFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val correo: TextInputEditText = view.findViewById(R.id.textCorreo1)
        val contraseña: TextInputEditText = view.findViewById(R.id.contraseña)
        val button: Button = view.findViewById(R.id.button)
        val button2: Button = view.findViewById(R.id.button2)
        val password: TextView = view.findViewById(R.id.textPassword)
        //

        password.setOnClickListener {
            val restablecerFragment = RestablecerFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, restablecerFragment).commit()

        }

        // esto es para hinabilitar el boton de guardar
        button2.isEnabled = false
        // esto es para que se cumplan los campos de textos y darle ese color trasparente al boton
        button2.alpha = 0.5f

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                button2.isEnabled =
                    (correo.text!!.isNotEmpty() && contraseña.text!!.isNotEmpty())
                button2.alpha = 1f
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }
        //Aqui estoy validando los campos de textos
        correo.addTextChangedListener(textWatcher)
        contraseña.addTextChangedListener(textWatcher)

//este es el boton para guardar los datos en getSharedPreferences y iniciar sesión
        button.setOnClickListener {
            val registroFragment = RegistroFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, registroFragment).commit()
        }
        button2.setOnClickListener {

            (requireActivity() as LoginActivity).apply {
                signInWithEmailAndPassword(correo.text.toString(), contraseña.text.toString())
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }
}