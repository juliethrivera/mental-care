package com.iegabrielamistral.mentalcare.usuario

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.MainActivity
import com.iegabrielamistral.mentalcare.R

class cuentaFragment : Fragment() {

    companion object {
        fun newInstance() = cuentaFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cuenta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val back: ImageView = view.findViewById(R.id.back)
        val textNombre: EditText = view.findViewById(R.id.textNombre)
        val textApellido: EditText = view.findViewById(R.id.textApellido)
        val textFecha: EditText = view.findViewById(R.id.textFecha)
        val textCorreo: EditText = view.findViewById(R.id.textCorreo)
        val textContraseña: EditText = view.findViewById(R.id.textContraseña)
        val guadar: Button = view.findViewById(R.id.guardar)

        guadar.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                guadar.isEnabled =
                    (textNombre.text.isNotEmpty() && textApellido.text.isNotEmpty()
                            && textFecha.text.isNotEmpty() && textCorreo.text.isNotEmpty()
                            && textContraseña.text.isNotEmpty())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }

        textNombre.addTextChangedListener(textWatcher)
        textApellido.addTextChangedListener(textWatcher)
        textFecha.addTextChangedListener(textWatcher)
        textCorreo.addTextChangedListener(textWatcher)
        textContraseña.addTextChangedListener(textWatcher)


        var pref = requireActivity().getSharedPreferences("datos_personales", Context.MODE_PRIVATE)

        var nombre = pref.getString("nombre", "")
        textNombre.setText(nombre)

        var apellido = pref.getString("apellido", "")
        textApellido.setText(apellido)

        var fecha = pref.getString("fecha", "")
        textFecha.setText(fecha)

        var correo = pref.getString("correo", "")
        textCorreo.setText(correo)

        var contraseña = pref.getString("contraseña", "")
        textContraseña.setText(contraseña)

        guadar.setOnClickListener {
            var pref =
                requireActivity().getSharedPreferences("nombre_personal", Context.MODE_PRIVATE)
            var editor = pref.edit()
            editor.putString("nombre", textNombre.text.toString())
            editor.putString("apellido", textApellido.text.toString())
            editor.putString("fecha", textFecha.text.toString())
            editor.putString("correo", textCorreo.text.toString())
            editor.putString("contraseña", textContraseña.text.toString())
            editor.commit()
            Toast.makeText(requireActivity(), "Se ha guardado exitosamente", Toast.LENGTH_LONG)
                .show()

            /*requireActivity().getSharedPreferences("datos personales", Context.MODE_PRIVATE)
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)*/

            (requireActivity() as LoginActivity).apply {
                createUserWithEmailAndPassword(
                    textCorreo.text.toString(),
                    textContraseña.text.toString()
                )
            }
        }
        back.setOnClickListener {
            val registroFragment = RegistroFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, registroFragment).commit()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }


}