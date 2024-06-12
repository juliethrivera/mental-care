package com.iegabrielamistral.mentalcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.iegabrielamistral.mentalcare.MainActivity
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.dialogs.MENSAJE
import com.iegabrielamistral.mentalcare.dialogs.MensajeBottomDialog

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    fun newInstance(): HomeFragment {
        val args = Bundle()

        val fragment = HomeFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val Mental : TextView = view.findViewById(R.id.Mental)
        val Text : TextView = view.findViewById(R.id.Text)
        val horribleImage : ImageView = view.findViewById(R.id.horrible_image)
        val horribleText : TextView = view.findViewById(R.id.horrible_text)
        val malImage : ImageView = view.findViewById(R.id.mal_imege)
        val malText : TextView = view.findViewById(R.id.mal_text)
        val aburridoImage: ImageView = view.findViewById(R.id.aburrido_image)
        val aburridoText : TextView = view.findViewById(R.id.aburrido_text)
        val bienImage : ImageView = view.findViewById(R.id.bien_image)
        val bienText : TextView = view.findViewById(R.id.bien_text)
        val excelenteImage : ImageView = view.findViewById(R.id.excelente_image)
        val excelenteText: TextView = view.findViewById(R.id.excelente_text)
        val cardTest : CardView = view.findViewById(R.id.card_test)
        val EjerciciosRelajación : CardView = view.findViewById(R.id.Ejercicios_relajación)

        val mensajesHorrible = listOf(
            "Aunque hoy te sientas roto, recuerda que los pedazos pueden reconstruirse más fuertes.",
            "Tu valentía para enfrentar este día ya te hace más fuerte de lo que crees.",
            "La tormenta más oscura precede al día más brillante. Aguanta un poco más, el sol volverá a brillar.",
            "No estás solo en esta batalla. Hay personas que te aman y están dispuestas a apoyarte.",
            "La cicatrización comienza desde adentro. Permítete sanar y crecer a tu propio ritmo."
        )
        val mensajesMal = listOf(
            "Hoy puede ser un mal día, pero mañana es una nueva oportunidad para empezar de nuevo.",
            "No estás solo en esta batalla; hay personas dispuestas a ayudarte y acompañarte en tu camino hacia la recuperación.",
            "La tormenta pasará, y cuando lo haga, estarás más fuerte y más sabio que antes.",
            "Tus emociones son validas, y está bien pedir ayuda cuando lo necesites.",
            "Cada respiración es un recordatorio de tu fuerza interior. Sigue adelante, un paso a la vez."
        )
        val mensajeAburrido = listOf(
            "Cada día es una nueva oportunidad para encontrar la felicidad en las pequeñas cosas.",
            "El aburrimiento es solo una pausa, no el final. Encuentra algo que te inspire.",
            "Tu mente es como un jardín; cultiva pensamientos positivos y verás florecer la alegría.",
            "La creatividad puede ser la chispa que encienda tu espíritu cuando te sientas aburrido.",
            "La vida está llena de aventuras esperando a ser vividas. ¡Ve y crea las tuyas!"
        )
        val mensajeBien = listOf(
            "Que la felicidad que sientes hoy ilumine cada rincón de tu vida.",
            "Sigue cultivando tu bienestar mental y emocional; mereces sentirte así todos los días.",
            "Hoy es un buen día para celebrar tu fuerza y determinación para cuidar tu salud mental.",
            "Que esta sensación de bienestar te inspire a seguir cuidándote y amándote a ti mismo.",
            "Tus logros en el camino hacia la salud mental son dignos de celebración. ¡Bravo!"
        )
        val mensajeExcelente = listOf(
            "Tu fortaleza interior es una inspiración para todos los que luchan por encontrar la paz.",
            "Que esta sensación de excelencia mental te motive a seguir cuidando y nutriendo tu bienestar.",
            "Eres un testimonio viviente de que la sanación es posible y que la felicidad es un viaje que vale la pena.",
            "Hoy brillas con una luz interior radiante. ¡Sigue brillando!",
            "Tu paz mental es una inspiración para todos los que te rodean.",
            "En este momento, eres la mejor versión de ti mismo. ¡Disfrútalo!"
        )

        horribleImage.setOnClickListener{
            mostrarMensajes(mensajesHorrible.random())
        }
        malImage.setOnClickListener{
            mostrarMensajes(mensajesMal.random())
        }
        aburridoImage.setOnClickListener{
            mostrarMensajes(mensajeAburrido.random())
        }
        bienImage.setOnClickListener{
            mostrarMensajes(mensajeBien.random())
        }
        excelenteImage.setOnClickListener{
            mostrarMensajes(mensajeExcelente.random())
        }
        cardTest.setOnClickListener{
            (activity as MainActivity).bnvView.selectedItemId = R.id.test
        }
        EjerciciosRelajación.setOnClickListener {
            (activity as MainActivity).bnvView.selectedItemId = R.id.RELAJACION
        }
    }

    fun mostrarMensajes(mensaje : String){
        val mensajeBottomDialog = MensajeBottomDialog()
        val bundle = Bundle()
        bundle.putString(MENSAJE, mensaje)
        mensajeBottomDialog.arguments = bundle
        mensajeBottomDialog.show(requireActivity().supportFragmentManager, "MensajeBottomDialog")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}