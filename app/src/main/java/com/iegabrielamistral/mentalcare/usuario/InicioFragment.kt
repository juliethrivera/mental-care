package com.iegabrielamistral.mentalcare.usuario

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.R

class InicioFragment : Fragment() {

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

        val telefono: TextView = view.findViewById(R.id.telefono)
        val contraseña: TextView = view.findViewById(R.id.contraseña)
        val button: Button = view.findViewById(R.id.button)
        val button2: Button = view.findViewById(R.id.button2)

        button2.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                button2.isEnabled =
                    (telefono.text.isNotEmpty() && contraseña.text.isNotEmpty())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }

        telefono.addTextChangedListener(textWatcher)
        contraseña.addTextChangedListener(textWatcher)


        button.setOnClickListener {
            val registroFragment = RegistroFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, registroFragment).commit()
        }
        button2.setOnClickListener {
            /*val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)

            Toast.makeText(requireActivity(),"La información es incorrecta ",Toast.LENGTH_LONG).show()
*/

            (requireActivity() as LoginActivity).apply {
                signInWithEmailAndPassword(telefono.text.toString(), contraseña.text.toString())
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }
}