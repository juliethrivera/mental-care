package com.iegabrielamistral.mentalcare.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.iegabrielamistral.mentalcare.R

class ResultadoTest : Fragment() {


    lateinit var atras : ImageView

    companion object {
        fun newInstance() = ResultadoTest()
    }

    private val viewModel: ResultadoTestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_resultado_test, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        atras = view.findViewById(R.id.atras)


        atras.setOnClickListener {
            val perfilUsuarioFragment = PerfilUsuarioFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, perfilUsuarioFragment).commit()
        }


    }


}