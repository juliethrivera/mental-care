package com.iegabrielamistral.mentalcare.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.iegabrielamistral.mentalcare.R

class VistaEjercicioFragment : Fragment() {

    private lateinit var anterior : ImageView
    private lateinit var titulo : TextView
    private lateinit var tiempo : ProgressBar
    private lateinit var cardEjercicio : CardView
    private lateinit var nombreEjercicio : TextView
    private lateinit var descripcion: TextView
    private lateinit var siguienteEjercicio : Button

    companion object {
        fun newInstance() = VistaEjercicioFragment()
    }

    private val viewModel: VistaEjercicioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel

    }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            anterior = view.findViewById(R.id.anterior)
            titulo = view.findViewById(R.id.titulo)
            tiempo = view.findViewById(R.id.tiempo)
            cardEjercicio = view.findViewById(R.id.cardEjercicio)
            nombreEjercicio = view.findViewById(R.id.nombreEjercicio)
            descripcion = view.findViewById(R.id.decripcion)
            siguienteEjercicio = view.findViewById(R.id.siguienteEjercicio)


            anterior.setOnClickListener{
                activity?.onBackPressedDispatcher?.onBackPressed()

            }

            siguienteEjercicio.setOnClickListener{




            }



        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return inflater.inflate(R.layout.fragment_vista_ejercicio, container, false)
    }
}