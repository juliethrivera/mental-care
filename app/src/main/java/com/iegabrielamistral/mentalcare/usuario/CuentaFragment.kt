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

        // Establece un listener de clic para el campo de texto 'editText', que al hacer clic abrirá un diálogo para seleccionar una fecha
        editText.setOnClickListener {
            // Llama a la función 'shwDatePikerDialog()' para mostrar un DatePickerDialog al usuario
            shwDatePikerDialog()
        }
        // esto es para hinabilitar el boton de guardar
        guardar.isEnabled = false
        // esto es para poner trasparente el boton de guardar
        guardar.alpha = 0.5f
        // Se crea un TextWatcher para monitorear los cambios en los campos de texto
        val textWatcher = object : TextWatcher {

            // Este método se llama antes de que el texto cambie
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // No se realiza ninguna acción antes de que el texto cambie
            }
            // Este método se llama cuando el texto ha cambiado
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Habilita o deshabilita el botón 'guardar' dependiendo de si todos los campos están llenos
                guardar.isEnabled =
                    (
                            textNombre.text!!.isNotEmpty() && textApellido.text!!.isNotEmpty() &&
                                    editText.text!!.isNotEmpty() && textCorreo.text!!.isNotEmpty() &&
                                    textContraseña.text!!.isNotEmpty()
                            )

                // Cambia la opacidad del botón 'guardar' dependiendo de su habilitación
                if (guardar.isEnabled) {
                    guardar.alpha = 1f  // Si está habilitado, se pone completamente opaco
                } else {
                    guardar.alpha = 0.5f  // Si está deshabilitado, se pone semi-transparente
                }
            }

            // Este método se llama después de que el texto ha cambiado
            override fun afterTextChanged(p0: Editable?) {
                // No se realiza ninguna acción después de que el texto cambie
            }
        }
        //Aqui estoy validando los campos de textos
        textNombre.addTextChangedListener(textWatcher)
        textApellido.addTextChangedListener(textWatcher)
        editText.addTextChangedListener(textWatcher)
        textCorreo.addTextChangedListener(textWatcher)
        textContraseña.addTextChangedListener(textWatcher)


        // Configura un listener para el botón 'guardar' que guarda los datos del usuario en SharedPreferences
        guardar.setOnClickListener {
            // Obtiene el objeto SharedPreferences, que se usará para guardar los datos de la actividad de manera persistente
            val pref = requireActivity().getSharedPreferences(
                requireActivity().packageName,
                Context.MODE_PRIVATE
            )
            // Se obtiene un editor de SharedPreferences para realizar las modificaciones
            val editor = pref.edit()
            // Guarda los datos introducidos por el usuario en las vistas (EditText) correspondientes
            editor.putString("nombre", textNombre.text.toString())  // Guarda el nombre
            editor.putString("apellido", textApellido.text.toString())  // Guarda el apellido
            editor.putString(
                "fecha",
                editText.text.toString()
            )  // Guarda la fecha (puede ser de nacimiento o registro)
            editor.putString("correo", textCorreo.text.toString())  // Guarda el correo electrónico
            editor.putString("contraseña", textContraseña.text.toString())  // Guarda la contraseña
            // Aplica los cambios realizados en SharedPreferences
            editor.apply()
            // Llama a la función de la actividad LoginActivity para crear el usuario con el correo y la contraseña
            (requireActivity() as LoginActivity).apply {
                // Crea un nuevo usuario utilizando el correo y la contraseña proporcionados
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

    // Función privada para mostrar un diálogo de selección de fecha utilizando un fragmento personalizado
    private fun shwDatePikerDialog() {
        // Crea una instancia del fragmento DatePickerFragment, pasando un lambda como callback para manejar la fecha seleccionada
        val datePicker = DatePickerFragment { day, month, year ->
            // Cuando el usuario selecciona una fecha, se llama al método 'onDateSelected' con los valores de día, mes y año
            onDateSelected(day, month, year)
        }

        // Muestra el fragmento del DatePicker en el FragmentManager de la actividad actual
        datePicker.show(requireActivity().supportFragmentManager, "datePicker")
    }

    // esto es para el calenario
    fun onDateSelected(day: Int, month: Int, year: Int) {
        //aqui le estoy agregando un numero mas al calendario ya que comensaba en 0 el mes
        var nmonth = month + 1
        //aqui estoy llamando el campo de texto donde va a aparecer y le estoy diciendo que comienza de esa manera
        editText.setText(" $day-$nmonth-$year")
    }
}

