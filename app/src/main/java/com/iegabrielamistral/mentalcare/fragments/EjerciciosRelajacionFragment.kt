package com.iegabrielamistral.mentalcare.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.iegabrielamistral.mentalcare.R


class EjerciciosRelajacionFragment : Fragment() {


    private lateinit var anterior : ImageButton
    private lateinit var ejerciciosDeRelajacion : TextView
    private lateinit var cardYoga : CardView
    private lateinit var imagenYoga : ImageView
    private lateinit var yoga : TextView
    private lateinit var descripcionYoga : TextView
    private lateinit var cardMeditacion : CardView
    private lateinit var imagenMeditacion : ImageView
    private lateinit var meditacion : TextView
    private lateinit var descripcionMeditacion : TextView
    private lateinit var cardRespiracionProfunda : CardView
    private lateinit var imagenRespiracionProfunda : ImageView
    private lateinit var respiracionProfunda : TextView
    private lateinit var descripcionRespiracionProfunda : TextView



    companion object {
        fun newInstance() = EjerciciosRelajacionFragment()
    }

    private val viewModel: EjerciciosRelajacionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        anterior = view.findViewById(R.id.anterior)
        ejerciciosDeRelajacion = view.findViewById(R.id.ejerciciosDeRelajacion)
        cardYoga = view.findViewById(R.id.cardYoga)
        imagenYoga = view.findViewById(R.id.imagenYoga)
        yoga = view.findViewById(R.id.yoga)
        descripcionYoga = view.findViewById(R.id.descripcionYoga)
        cardMeditacion = view.findViewById(R.id.cardMeditacion)
        imagenMeditacion = view.findViewById(R.id.imagenMeditacion)
        meditacion = view.findViewById(R.id.meditacion)
        descripcionMeditacion = view.findViewById(R.id.descripcionMeditacion)
        cardRespiracionProfunda = view.findViewById(R.id.cardRespiracionProfunda)
        imagenRespiracionProfunda = view.findViewById(R.id.imagenRespiracionProfunda)
        respiracionProfunda = view.findViewById(R.id.respiracionProfunda)
        descripcionRespiracionProfunda = view.findViewById(R.id.descripcionRespiracionProfunda)



        anterior.setOnClickListener{
            activity?.onBackPressedDispatcher?.onBackPressed()
        }


        yoga.setOnClickListener{

        }

        meditacion.setOnClickListener{

        }

        ejerciciosDeRelajacion.setOnClickListener{

        }


    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_ejercicios_relajacion, container, false)
    }
}

