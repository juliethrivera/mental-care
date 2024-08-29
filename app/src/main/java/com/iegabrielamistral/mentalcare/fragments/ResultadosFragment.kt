package com.iegabrielamistral.mentalcare.fragments

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

        anterior = view.findViewById(R.id.anterior)
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


        progresoEstres.progress = selecciones[0]
        progresoAnsiedad.progress = selecciones[1]
        progresoDepresion.progress = selecciones[2]
        progresoHumor.progress = selecciones[3]
        progresoAutoestima.progress = selecciones[4]



        anterior.setOnClickListener {
            val testMentalBlankFragment = TestMentalBlankFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, testMentalBlankFragment).commit()

        }

    }
}