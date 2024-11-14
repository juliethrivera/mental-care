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
import androidx.annotation.RequiresApi
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.Usuario

class CuentaFragment : Fragment() {


    companion object {
        fun newInstance() = CuentaFragment()
    }
    // aqui estoy declarando los componentes del layout

    lateinit var editText: TextInputEditText
    lateinit var textContraseña: TextInputEditText
    lateinit var textCorreo: TextInputEditText
    lateinit var textApellido: TextInputEditText
    lateinit var textNombre: TextInputEditText
    lateinit var guardar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cuenta, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //aqui estoy inicializando los componentes del layout

        val back: Button = view.findViewById(R.id.back)
        textNombre = view.findViewById(R.id.textNombre)
        textApellido = view.findViewById(R.id.textApellido)
        textCorreo = view.findViewById(R.id.textCorreo)
        textContraseña = view.findViewById(R.id.textContraseña)
        guardar = view.findViewById(R.id.guardar)
        editText = view.findViewById(R.id.Editext)

        editText.setOnClickListener {
            shwDatePikerDialog()
        }
        // esto es para hinabilitar el boton de guardar
        guardar.isEnabled = false
        // esto es para poner trasparente el boton de guardar
        guardar.alpha = 0.5f
       // esto es para que se cumplan los campos de textos y darle ese color trasparente al boton
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                guardar.isEnabled =
                    (
                            textNombre.text!!.isNotEmpty() && textApellido.text!!.isNotEmpty() &&
                                    editText.text!!.isNotEmpty() && textCorreo.text!!.isNotEmpty()
                                    && textContraseña.text!!.isNotEmpty())

                if(guardar.isEnabled){
                    guardar.alpha = 1f
                }else{
                    guardar.alpha = 0.5f
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }
       //Aqui estoy validando los campos de textos
        textNombre.addTextChangedListener(textWatcher)
        textApellido.addTextChangedListener(textWatcher)
        editText.addTextChangedListener(textWatcher)
        textCorreo.addTextChangedListener(textWatcher)
        textContraseña.addTextChangedListener(textWatcher)

        //este es el boton para guardar los datos en getSharedPreferences y iniciar sesión
        guardar.setOnClickListener {
            val pref =
                requireActivity().getSharedPreferences(requireActivity().packageName, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("nombre", textNombre.text.toString())
            editor.putString("apellido", textApellido.text.toString())
            editor.putString("fecha", editText.text.toString())
            editor.putString("correo", textCorreo.text.toString())
            editor.putString("contraseña", textContraseña.text.toString())
            editor.apply()

            (requireActivity() as LoginActivity).apply {
                createUserWithEmailAndPassword(
                    textCorreo.text.toString(),
                    textContraseña.text.toString()
                )
            }
        }
      // esto es para regresar a la vista anterior
        back.setOnClickListener {
            val registroFragment = RegistroFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, registroFragment).commit()
        }


    }

        // aqui estoy llamando la clase del dataPiker
    private fun shwDatePikerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(requireActivity().supportFragmentManager, "datePicker")

    }
        // esto es para el calenario
    fun onDateSelected(day: Int, month: Int, year: Int) {

        var nmonth = month + 1

        editText.setText(" $day-$nmonth-$year")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }


}

