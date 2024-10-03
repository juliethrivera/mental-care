package com.iegabrielamistral.mentalcare.usuario

import android.content.Context
import android.os.Build
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
import androidx.annotation.RequiresApi
import com.google.android.material.textfield.TextInputEditText
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.R

class CuentaFragment : Fragment() {

    companion object {
        fun newInstance() = CuentaFragment()
    }

    lateinit var editText: TextInputEditText
    lateinit var textContraseña: TextInputEditText
    lateinit var textCorreo: TextInputEditText
    lateinit var textApellido: TextInputEditText
    lateinit var textNombre: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cuenta, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val back: Button = view.findViewById(R.id.back)
        textNombre = view.findViewById(R.id.textNombre)
        textApellido = view.findViewById(R.id.textApellido)
        textCorreo = view.findViewById(R.id.textCorreo)
        textContraseña = view.findViewById(R.id.textContraseña)
        val guadar: Button = view.findViewById(R.id.guardar)

        editText = view.findViewById(R.id.Editext)


        editText.setOnClickListener {
            shwDatePikerDialog()
        }

        guadar.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                guadar.isEnabled =
                    (
                            textNombre.text!!.isNotEmpty() && textApellido.text!!.isNotEmpty() &&
                                    editText.text!!.isNotEmpty() && textCorreo.text!!.isNotEmpty()
                                    && textContraseña.text!!.isNotEmpty())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }

        textNombre.addTextChangedListener(textWatcher)
        textApellido.addTextChangedListener(textWatcher)
        editText.addTextChangedListener(textWatcher)
        textCorreo.addTextChangedListener(textWatcher)
        textContraseña.addTextChangedListener(textWatcher)


        var pref = requireActivity().getSharedPreferences("datos_personales", Context.MODE_PRIVATE)

        var nombre = pref.getString("nombre", "")
        textNombre.setText(nombre)

        var apellido = pref.getString("apellido", "")
        textApellido.setText(apellido)

        var fecha = pref.getString("fecha", "")
        editText.setText(fecha)

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
            editor.putString("fecha", editText.text.toString())
            editor.putString("correo", textCorreo.text.toString())
            editor.putString("contraseña", textContraseña.text.toString())
            editor.commit()
            /*Toast.makeText(requireActivity(), "Se ha guardado exitosamente", Toast.LENGTH_LONG)
                .show()*/

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

    private fun shwDatePikerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(requireActivity().supportFragmentManager, "datePicker")

    }

    fun onDateSelected(day: Int, month: Int, year: Int) {

        var nmonth = month + 1

        editText.setText(" $day-$nmonth-$year")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }


}

