package com.iegabrielamistral.mentalcare.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.iegabrielamistral.mentalcare.MainActivity
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.dialogs.MENSAJE
import com.iegabrielamistral.mentalcare.dialogs.MensajeBottomDialog
import java.lang.reflect.Array.getInt

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
    // Declaración de una variable 'card' de tipo CardView que se inicializará mas abajo
    lateinit var card : CardView


    // TODO: Rename and change types of parameters
    // Declaración de dos parámetros opcionales de tipo String que se inicializan como null
    private var param1: String? = null
    private var param2: String? = null

    // Función estática para crear una nueva instancia del fragmento HomeFragment sin parámetros
    fun newInstance(): HomeFragment {
        // Crea un nuevo objeto Bundle, que puede almacenar datos para el fragmento
        val args = Bundle()

        // Crea una nueva instancia del fragmento HomeFragment
        val fragment = HomeFragment()

        // Asocia el Bundle vacío (args) con los argumentos del fragmento
        fragment.arguments = args

        // Devuelve la nueva instancia del fragmento
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

        // Inicializa las vistas (ImageView, TextView, CardView) a partir del layout del fragmento
        val horribleImage: ImageView = view.findViewById(R.id.horrible_image)  // Imagen para "horrible"
        val horribleText: TextView = view.findViewById(R.id.horrible_text)  // Texto para "horrible"
        val malImage: ImageView = view.findViewById(R.id.mal_imege)  // Imagen para "mal"
        val malText: TextView = view.findViewById(R.id.mal_text)  // Texto para "mal"
        val aburridoImage: ImageView = view.findViewById(R.id.aburrido_image)  // Imagen para "aburrido"
        val aburridoText: TextView = view.findViewById(R.id.aburrido_text)  // Texto para "aburrido"
        val bienImage: ImageView = view.findViewById(R.id.bien_image)  // Imagen para "bien"
        val bienText: TextView = view.findViewById(R.id.bien_text)  // Texto para "bien"
        val excelenteImage: ImageView = view.findViewById(R.id.excelente_image)  // Imagen para "excelente"
        val excelenteText: TextView = view.findViewById(R.id.excelente_text)  // Texto para "excelente"
        val cardTest: CardView = view.findViewById(R.id.card_test)  // CardView para "test"
        val EjerciciosRelajación: CardView = view.findViewById(R.id.Ejercicios_relajación)  // CardView para "ejercicios de relajación"
        card = view.findViewById(R.id.card_edi)  // CardView para "editar"

        // esto son todas las listas de consejos y recomendación para cada icono

        val mensajesHorrible = listOf(

            "A veces, lo que necesitamos es simplemente seguir, incluso cuando es difícil. El cambio llegará.\nRecomendación: Mantén una rutina básica, incluso en los días difíciles. A veces, el simple acto de seguir adelante puede hacer una gran diferencia en cómo te sientes.",
            "Cada día es una nueva oportunidad para sanar y avanzar, por más pequeño que sea el paso.\nRecomendación: Establece metas pequeñas y alcanzables cada día. Lo importante no es la rapidez, sino avanzar a tu propio ritmo.",
            "Permítete el tiempo de sentir y sanar, no te apresures, tu bienestar es prioridad.\nRecomendación: No te presiones para (esta bien) rápidamente. Dale espacio a tus emociones y permítete sanar a tu propio ritmo.",
            "Eres más fuerte de lo que crees, y lo has demostrado cada vez que te has levantado tras caer.\nRecomendación:Reflexiona sobre situaciones en las que te has recuperado antes. Mantén una libreta de (victorias pasadas) para recordarte tu resiliencia.",
            "No tienes que cargar con todo solo, está bien pedir ayuda.\nRecomendación: Busca a alguien de confianza con quien puedas hablar. A veces, compartir tus pensamientos con un amigo o un profesional puede hacer una gran diferencia."
        )
        val mensajesMal = listOf(

            "A veces, descansar es la mejor manera de prepararte para seguir adelante.\nRecomendación: Tómate un día de descanso completo. Apaga el teléfono, pon una música relajante y dedica tiempo a descansar sin culpa.",
            "No tienes que tener todas las respuestas ahora mismo, solo enfócate en lo que puedes hacer hoy.\nRecomendación: Divide tus problemas en tareas más pequeñas. Concéntrate en resolver un solo aspecto a la vez para no sentirte abrumado por todo de golpe.",
            "Dale tiempo al tiempo; todo lo que sientes también pasará.\nRecomendación: Practica la paciencia contigo mismo. Cuando te sientas abrumado, recuérdate que no tienes que resolver todo de inmediato. Cada día es una nueva oportunidad.",
            "No te castigues por sentirte mal, cada emoción es válida y merece ser escuchada.\nRecomendación: Practica la autoaceptación. En lugar de juzgar tus emociones, escribe en un diario cómo te sientes para procesarlas de manera saludable.",
            "Incluso en los momentos difíciles, hay pequeños pasos que puedes tomar para mejorar tu día.\nRecomendación: Intenta realizar una pequeña acción que te haga sentir mejor, como preparar una taza de té, dar un paseo o leer un libro que te guste."
        )
        val mensajeAburrido = listOf(

            "A veces, el aburrimiento es el espacio perfecto para que surjan grandes ideas.\nRecomendación: Tómate unos minutos para reflexionar o escribir en un diario. A menudo, las ideas más creativas surgen cuando tu mente está en reposo.",
            "Cuando te sientas aburrido, prueba algo que siempre has querido hacer pero nunca has tenido tiempo.\nRecomendación: Haz una lista de cosas que has querido aprender o hacer (como leer un libro pendiente, empezar a aprender un idioma o hacer una manualidad) y elige una para empezar hoy.",
            "El aburrimiento puede ser un buen momento para reconectar contigo mismo, sin distracciones externas.\nRecomendación: Apaga las distracciones tecnológicas durante un rato. Haz una pausa para meditar, reflexionar o simplemente estar en silencio contigo mismo.",
            "Un cambio de ambiente, por simple que sea, puede transformar el aburrimiento en inspiración.\nRecomendación: Sal a caminar o cambia tu entorno. A veces, una caminata al aire libre o reorganizar tu espacio puede hacer que te sientas renovado.",
            "Aprovecha el aburrimiento para desconectar y recargar energías, a veces no hacer nada también es productivo.\nRecomendación: Usa este tiempo de aburrimiento para descansar conscientemente. Haz una siesta, medita o simplemente disfruta de estar presente sin sentir la presión de ser productivo."
        )

        val mensajeBien = listOf(

            "Estar bien también es el momento perfecto para reflexionar y agradecer todo lo que has logrado hasta aquí.\nRecomendación: Haz una lista de tus logros recientes y las lecciones aprendidas. Reflexionar sobre tu progreso te ayudará a valorar tu crecimiento y seguir adelante con confianza.",
            "Disfruta y agradece lo bien que te sientes, cada momento positivo es un tesoro.\nRecomendación: Practica la gratitud. Tómate unos minutos para reflexionar o escribir sobre las cosas por las que estás agradecido hoy. Esto refuerza tu bienestar emocional.",
            "Aprovecha esta buena racha para seguir aprendiendo y creciendo, el bienestar es la mejor base para avanzar.\nRecomendación: Inscríbete en un curso o taller que te interese, o comienza a leer sobre un tema nuevo. Usa tu estado mental positivo para absorber conocimientos con más facilidad.",
            "Cuando te sientas bien, estás en el mejor estado para dar lo mejor de ti y seguir avanzando.\nRecomendación: Identifica un proyecto o tarea que hayas postergado. Con tu energía positiva, es el momento perfecto para enfocarte y avanzar en ello.",
            "Usa este bienestar para recargar tus fuerzas y planificar metas que te sigan inspirando.\nRecomendación: Establece nuevas metas a corto y largo plazo mientras tienes claridad mental. Piensa en algo que te entusiasme y planifica cómo lograrlo."
        )
        val mensajeExcelente = listOf(

            "Cuando te sientes tan bien, estás en el momento perfecto para ir tras esos sueños que te apasionan.\nRecomendación: Haz una lista de tus sueños o metas personales y establece los primeros pasos para acercarte a ellos. Aprovecha tu motivación y empieza con algo pequeño pero significativo.",
            "Sentirte excelente es una señal de que estás alineado contigo mismo; sigue cultivando lo que te hace feliz.\nRecomendación: Reflexiona sobre las actividades y personas que contribuyen a tu felicidad y bienestar. Haz un compromiso de mantener estas cosas presentes en tu vida regularmente.",
            "Aprovecha esta energía para iniciar proyectos nuevos o darle un impulso extra a los que ya tienes.\nRecomendación: Elige un proyecto en el que quieras avanzar o uno que quieras comenzar. Dedica un tiempo concentrado a trabajar en él y deja que tu energía positiva impulse tu productividad.",
            "Usa este buen momento para fortalecer tus relaciones, disfrutar con quienes amas y crear recuerdos inolvidables.\nRecomendación: Organiza un encuentro especial, ya sea una cena, un paseo o una actividad divertida con las personas que más quieres. Estos recuerdos se convierten en tesoros.",
            "Estar en un estado tan positivo te da claridad; utiliza esta perspectiva para tomar decisiones importantes.\nRecomendación: Reflexiona sobre cualquier decisión o cambio que hayas estado posponiendo. Tu mente clara y positiva puede ayudarte a ver opciones y soluciones que antes no habías considerado."
        )

        // Establece un listener para el clic en la imagen "horribleImage"
        // Al hacer clic, se muestra un mensaje aleatorio de la lista mensajesHorrible
        horribleImage.setOnClickListener {
            mostrarMensajes(mensajesHorrible.random())
        }

        // Establece un listener para el clic en la imagen "malImage"
        // Al hacer clic, se muestra un mensaje aleatorio de la lista mensajesMal
        malImage.setOnClickListener {
            mostrarMensajes(mensajesMal.random())
        }

        // Establece un listener para el clic en la imagen "aburridoImage"
        // Al hacer clic, se muestra un mensaje aleatorio de la lista mensajeAburrido
        aburridoImage.setOnClickListener {
            mostrarMensajes(mensajeAburrido.random())
        }

        // Establece un listener para el clic en la imagen "bienImage"
        // Al hacer clic, se muestra un mensaje aleatorio de la lista mensajeBien
        bienImage.setOnClickListener {
            mostrarMensajes(mensajeBien.random())
        }

        // Establece un listener para el clic en la imagen "excelenteImage"
        // Al hacer clic, se muestra un mensaje aleatorio de la lista mensajeExcelente
        excelenteImage.setOnClickListener {
            mostrarMensajes(mensajeExcelente.random())
        }

        // Establece un listener para el clic en la tarjeta "cardTest"
        // Al hacer clic, selecciona el ítem "test" en el BottomNavigationView de MainActivity
        cardTest.setOnClickListener {
            (activity as MainActivity).bnvView.selectedItemId = R.id.test
        }

        // Establece un listener para el clic en "EjerciciosRelajación"
        // Al hacer clic, selecciona el ítem "Relajación" en el BottomNavigationView de MainActivity
        EjerciciosRelajación.setOnClickListener {
            (activity as MainActivity).bnvView.selectedItemId = R.id.Relajación
        }

        // Obtiene el SharedPreferences asociado a la actividad actual en modo privado
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        sharedPref?.apply {
            // Recupera los valores guardados en las SharedPreferences utilizando las claves definidas
            // Si no existen, se asigna un valor por defecto (0 en este caso)
            val lista = listOf(
                getInt(SAVED_RESULTADO_1, 0),
                getInt(SAVED_RESULTADO_2, 0),
                getInt(SAVED_RESULTADO_3, 0),
                getInt(SAVED_RESULTADO_4, 0),
                getInt(SAVED_RESULTADO_5, 0)
            )

            // Establece un listener para el clic en el card
            card.setOnClickListener {
                // Crea una nueva instancia de ResultadosFragment y le pasa la lista de resultados como argumento
                val resultadosFragment = ResultadosFragment(lista)

                // Reemplaza el fragmento actual con el nuevo ResultadosFragment en el contenedor especificado
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, resultadosFragment)  // Reemplaza el fragmento en el contenedor
                    .commit()  // Realiza la transacción
            }
        }


    }
    // esta función es para mostrar los mensajes que contienes los iconos

    // Función que muestra un diálogo con un mensaje en un fragmento
    fun mostrarMensajes(mensaje: String) {
        // Crea una nueva instancia del fragmento MensajeBottomDialog
        val mensajeBottomDialog = MensajeBottomDialog()

        // Crea un bundle para pasar datos al fragmento
        val bundle = Bundle()
        // Pone el mensaje en el bundle, usando la constante MENSAJE como clave
        bundle.putString(MENSAJE, mensaje)

        // Asigna el bundle como argumento del fragmento
        mensajeBottomDialog.arguments = bundle

        // Muestra el fragmento en la actividad actual usando el FragmentManager
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
        // Función estática para crear una nueva instancia de HomeFragment con parámetros personalizados
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                // Crea un Bundle para pasar los parámetros al fragmento
                arguments = Bundle().apply {
                    // Añade los parámetros al Bundle usando claves definidas (ARG_PARAM1, ARG_PARAM2)
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}