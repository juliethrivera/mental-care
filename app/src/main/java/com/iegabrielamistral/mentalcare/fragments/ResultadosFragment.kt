package com.iegabrielamistral.mentalcare.fragments

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.iegabrielamistral.mentalcare.R

class ResultadosFragment(val selecciones: List<Int>) : Fragment() {

    private lateinit var anterior: ImageView
    private lateinit var progresoDepresion : ProgressBar
    private lateinit var porcentajeDepresion : TextView
    private lateinit var progresoAnsiedad : ProgressBar
    private lateinit var porcentajeAnsiedad : TextView
    private lateinit var progresoAutoestima : ProgressBar
    private lateinit var porcentajeAutoestima : TextView
    private lateinit var progresoEstres : ProgressBar
    private lateinit var porcentajeEstres : TextView
    private lateinit var progresoHumor : ProgressBar
    private lateinit var porcentajeHumor : TextView

    private val viewModel: ResultadosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_resultados, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        anterior = view.findViewById(R.id.home)
        progresoDepresion = view.findViewById(R.id.progresoDepresion)
        porcentajeDepresion = view.findViewById(R.id.porcentajeDepresion)
        progresoAnsiedad= view.findViewById(R.id.progresoAnsiedad)
        porcentajeAnsiedad = view.findViewById(R.id.porcentajeAnsiedad)
        progresoAutoestima = view.findViewById(R.id.progresoAutoestima)
        porcentajeAutoestima = view.findViewById(R.id.porcentajeAutoestima)
        progresoEstres = view.findViewById(R.id.progresoEstres)
        porcentajeEstres = view.findViewById(R.id.porcentajeEstres)
        progresoHumor = view.findViewById(R.id.progresoHumor)
        porcentajeHumor = view.findViewById(R.id.porcentajeHumor)

        porcentajeEstres.text = selecciones[0].toString() + "%"
        porcentajeAnsiedad.text = selecciones[1].toString() + "%"
        porcentajeDepresion.text = selecciones[2].toString() + "%"
        porcentajeHumor.text = selecciones[3].toString() + "%"
        porcentajeAutoestima.text = selecciones[4].toString() + "%"


        ObjectAnimator.ofInt(progresoEstres, "progress", selecciones[0])
            .setDuration(1000)
            .start()

        ObjectAnimator.ofInt(progresoDepresion, "progress", selecciones[2])
            .setDuration(1000)
            .start();

        ObjectAnimator.ofInt(progresoHumor, "progress", selecciones[3])
            .setDuration(1000)
            .start();

        ObjectAnimator.ofInt(progresoAutoestima, "progress", selecciones[4])
            .setDuration(1000)
            .start();

        ObjectAnimator.ofInt(progresoAnsiedad, "progress", selecciones[1])
            .setDuration(1000)
            .start();


        anterior.setOnClickListener {
            val HomeFragment= HomeFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, HomeFragment).commit()

        }

    }
}