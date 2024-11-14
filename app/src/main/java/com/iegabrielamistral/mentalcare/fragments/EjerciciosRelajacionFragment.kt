package com.iegabrielamistral.mentalcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.meditacion.MeditacionFragment

// se creo una clase
class EjerciciosRelajacionFragment : Fragment() {

    //declare las variables de lo que contiene la vista de ejercicios de relajacion
    private lateinit var ejerciciosDeRelajacion: TextView
    private lateinit var cardYoga: CardView
    private lateinit var imagenYoga: ImageView
    private lateinit var yoga: TextView
    private lateinit var descripcionYoga: TextView
    private lateinit var cardMeditacion: CardView
    private lateinit var imagenMeditacion: ImageView
    private lateinit var meditacion: TextView
    private lateinit var descripcionMeditacion: TextView
    private lateinit var cardRespiracionProfunda: CardView
    private lateinit var imagenRespiracionProfunda: ImageView
    private lateinit var respiracionProfunda: TextView
    private lateinit var descripcionRespiracionProfunda: TextView


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

        //inicialice las variables
        ejerciciosDeRelajacion = view.findViewById(R.id.titulo)
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



        // Se establece un listener para el clic en el cardYoga
        cardYoga.setOnClickListener {
            // Creamos una nueva instancia del fragmento YogaFragment
            val yogaFragment = YogaFragment()
            // Iniciamos una transacción de fragmentos
            requireActivity().supportFragmentManager.beginTransaction()
                // Reemplazamos el fragmento actual con el nuevo fragmento (YogaFragment)
                .replace(R.id.fragmentContainerView, yogaFragment).commit()
        }
        cardMeditacion.setOnClickListener {
            val meditacionFragment = MeditacionFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, meditacionFragment).commit()
        }
        cardRespiracionProfunda.setOnClickListener {
            val respiracionProfundaFragment = RespiracionProfundaFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, respiracionProfundaFragment).commit()

        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_ejercicios_relajacion, container, false)
    }
}


