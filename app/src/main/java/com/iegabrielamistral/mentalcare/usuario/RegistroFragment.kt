package com.iegabrielamistral.mentalcare.usuario

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.iegabrielamistral.mentalcare.R

class RegistroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val iniciar : Button = view.findViewById(R.id.iniciar)
        val crear : Button = view.findViewById(R.id.crear)
        val facebook : ImageView = view.findViewById(R.id.facebook)
        val google : ImageView = view.findViewById(R.id.google)

        iniciar.setOnClickListener {
            val inicioFragment = inicioFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, inicioFragment).commit()
        }
        crear.setOnClickListener {
            val cuentaFragment = cuentaFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, cuentaFragment).commit()
        }
        facebook.setOnClickListener {

        }
        google.setOnClickListener {

        }


    }

    companion object {
        fun newInstance() = RegistroFragment()
    }

    private val viewModel: RegistroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }


}