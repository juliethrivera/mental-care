package com.iegabrielamistral.mentalcare.usuario

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.iegabrielamistral.mentalcare.MainActivity
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.fragments.HomeFragment

class cuentaFragment : Fragment() {

    companion object {
        fun newInstance() = cuentaFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cuenta, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val back: ImageView = view.findViewById(R.id.back)
        val nombre: EditText = view.findViewById(R.id.nombre)
        val fecha: EditText = view.findViewById(R.id.fecha)
        val correo: EditText = view.findViewById(R.id.correo)
        val contraseña: EditText = view.findViewById(R.id.contraseña)
        val guadar: Button = view.findViewById(R.id.guardar)

        back.setOnClickListener {
            val registroFragment = RegistroFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, registroFragment).commit()
        }
        nombre.setOnClickListener {

        }
        fecha.setOnClickListener {

        }
        correo.setOnClickListener {

        }
        contraseña.setOnClickListener {

        }
        guadar.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }


}