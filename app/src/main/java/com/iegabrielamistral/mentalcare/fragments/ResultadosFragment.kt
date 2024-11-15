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

    //se declararon los variables
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

        //se inicializaron las variables
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

        // Asignamos los valores de las selecciones a los TextViews correspondientes con el porcentaje
        porcentajeEstres.text = selecciones[0].toString() + "%"
        porcentajeAnsiedad.text = selecciones[1].toString() + "%"
        porcentajeDepresion.text = selecciones[2].toString() + "%"
        porcentajeHumor.text = selecciones[3].toString() + "%"
        porcentajeAutoestima.text = selecciones[4].toString() + "%"


        // Animamos la barra de progreso de Estrés con el valor almacenado en 'selecciones[0]'
        ObjectAnimator.ofInt(progresoEstres, "progress", selecciones[0])
            .setDuration(1000)
            .start()

        // Animamos la barra de progreso de Depresión con el valor almacenado en 'selecciones[2]'
        ObjectAnimator.ofInt(progresoDepresion, "progress", selecciones[2])
            .setDuration(1000)
            .start();

        // Animamos la barra de progreso de Humor con el valor almacenado en 'selecciones[3]'
        ObjectAnimator.ofInt(progresoHumor, "progress", selecciones[3])
            .setDuration(1000)
            .start();

        // Animamos la barra de progreso de Autoestima con el valor almacenado en 'selecciones[4]'
        ObjectAnimator.ofInt(progresoAutoestima, "progress", selecciones[4])
            .setDuration(1000)
            .start();

        // Animamos la barra de progreso de Ansiedad con el valor almacenado en 'selecciones[1]'
        ObjectAnimator.ofInt(progresoAnsiedad, "progress", selecciones[1])
            .setDuration(1000)
            .start();


        // Configuramos un listener para el botón 'anterior' que lleva al usuario de vuelta al fragmento de inicio
        anterior.setOnClickListener {
            // Creamos una nueva instancia de 'HomeFragment'
            val HomeFragment= HomeFragment()
            // Iniciamos una transacción para reemplazar el fragmento actual con 'HomeFragment'
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, HomeFragment).commit()

        }


    }
}