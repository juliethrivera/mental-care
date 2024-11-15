package com.iegabrielamistral.mentalcare.fragments

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.meditacion.MeditacionFragment
import com.iegabrielamistral.mentalcare.model.Ejercicio
import com.iegabrielamistral.mentalcare.model.`EjerciciosRelajacion`
import org.json.JSONObject
import java.io.InputStream
//CLASE
class VistaEjercicioFragment : Fragment() {

    //declare las variables
    private lateinit var anterior: ImageView
    private lateinit var titulo: TextView
    private lateinit var tiempo: ImageView
    private lateinit var contador: FrameLayout
    private lateinit var texto_contador: TextView
    private lateinit var cardEjercicio: CardView
    private lateinit var imagenEjercicio: ImageView
    private lateinit var nombreEjercicio: TextView
    private lateinit var descripcion: TextView
    private lateinit var siguienteEjercicio: Button
    private lateinit var empezarTiempo: Button

    // Variable que guardará la instancia del temporizador (CountDownTimer). Inicialmente está en null.
    var timer: CountDownTimer? = null

    // Variable que guarda el número del ejercicio actual. Comienza en 0.
    var numeroEjercicio = 0

    companion object {
        fun newInstance() = VistaEjercicioFragment()
    }

    private val viewModel: VistaEjercicioViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //inicialice las variables
        anterior = view.findViewById(R.id.home)
        titulo = view.findViewById(R.id.titulo)
        contador = view.findViewById(R.id.contador)
        texto_contador = view.findViewById(R.id.texto_contador)
        tiempo = view.findViewById(R.id.tiempo)
        cardEjercicio = view.findViewById(R.id.cardEjercicio)
        imagenEjercicio = view.findViewById(R.id.imagenEjercicio)
        nombreEjercicio = view.findViewById(R.id.nombreEjercicio)
        descripcion = view.findViewById(R.id.decripcion)
        siguienteEjercicio = view.findViewById(R.id.siguienteEjercicio)
        empezarTiempo = view.findViewById(R.id.empezarTiempo)



        // Se obtiene el tipo de ejercicio desde los argumentos del fragmento actual.
        // Si no se encuentra el tipo de ejercicio, se asigna el valor por defecto "YOGA".
        val tipoEjercicio = requireArguments().getString(TIPO_EJERCICIO, YOGA)

        // Se establece un OnClickListener para el botón o acción "anterior".
        anterior.setOnClickListener {
            // Se crea una instancia de un fragmento vacío (por defecto, para manejarlo más tarde).
            var fragment = Fragment()

            // Dependiendo del tipo de ejercicio (obtenido previamente), se selecciona el fragmento correspondiente.
            when(tipoEjercicio){
                YOGA -> {
                    fragment = YogaFragment() // Si el tipo es "YOGA", se carga el fragmento YogaFragment.
                }
                MEDITACION -> {
                    fragment = MeditacionFragment() // Si el tipo es "MEDITACION", se carga el fragmento MeditacionFragment.
                }
                RESPIRACION_PROFUNDA -> {
                    fragment = RespiracionProfundaFragment() // Si el tipo es "RESPIRACION PROFUNDA", se carga el fragmento RespiracionProfundaFragment.
                }
            }
            // Se realiza la transacción para reemplazar el fragmento actual por el nuevo fragmento seleccionado.
            requireActivity().supportFragmentManager.beginTransaction()
                // Reemplaza el fragmento en el contenedor especificado.
                .replace(R.id.fragmentContainerView, fragment).commit()
        }


        // Usamos una expresión 'when' para determinar el tipo de ejercicio y cargar el archivo JSON correspondiente
        val jsonString = when (tipoEjercicio) {
            // Si el tipo de ejercicio es 'YOGA', se lee el archivo 'yoga.json' desde los recursos raw
            YOGA -> {
                readJsonFromRaw(requireContext(), R.raw.yoga)
            }
            // Si el tipo de ejercicio es 'MEDITACION', se lee el archivo 'MEDITACION.json' desde los recursos raw
            MEDITACION -> {
                readJsonFromRaw(requireContext(), R.raw.meditacion)
            }
            // Si el tipo de ejercicio es cualquier otro valor (por ejemplo, 'RESPIRACION_PROFUNDA'), se lee el archivo 'respiracion_profunda.json'
            else -> {
                readJsonFromRaw(requireContext(), R.raw.respiracion_profunda)
            }
        }

        // Se crea una instancia de Gson, que es una librería para convertir objetos Java a JSON y viceversa
        val gson = Gson()

        // Usamos 'fromJson' para convertir el JSON (jsonString) en un objeto de la clase 'EjerciciosRelajacion'
        // 'TypeToken' se usa para proporcionar la información de tipo genérico de 'EjerciciosRelajacion'
        val data: `EjerciciosRelajacion` =
            gson.fromJson(jsonString, object : TypeToken<`EjerciciosRelajacion`>() {}.type)

        // Accedemos a la lista de 'ejercicios' que se encuentra en el objeto 'data' (el que fue deserializado desde el JSON)
        val ejercicios = data.ejercicios


        // Llamamos a la función 'cargarSiguienteEjercicio' y le pasamos el ejercicio correspondiente
        // Según el índice 'numeroEjercicio' (que indica cuál es el siguiente ejercicio)
        cargarSiguienteEjercicio(ejercicios[numeroEjercicio])



        siguienteEjercicio.setOnClickListener {
            // Incrementamos el contador 'numeroEjercicio' para avanzar al siguiente ejercicio
            numeroEjercicio++
            // Se imprime en el log el valor actual de 'numeroEjercicio' y el tamaño de la lista 'ejercicios'
            Log.d("EjercicioFragment", "numeroEjercicio $numeroEjercicio   ${ejercicios.size}")
            // Comprobamos si el número de ejercicio actual es menor que el total de ejercicios
            if (numeroEjercicio < ejercicios.size ) {
                // Si hay más ejercicios, cargar el siguiente ejercicio
                cargarSiguienteEjercicio(ejercicios[numeroEjercicio])

            }else if(numeroEjercicio == ejercicios.size){
                // Si ya se han mostrado todos los ejercicios, cambiar el texto a "Finalizar"
                siguienteEjercicio.text = "Finalizar"
            }
            else{
                // Si el número de ejercicio es mayor que el tamaño de la lista, finalizar y mostrar fragmento de relajación
                val ejerciciosRelajacionFragment = EjerciciosRelajacionFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, ejerciciosRelajacionFragment).commit()
            }
        }

    }


    fun cargarSiguienteEjercicio(ejercicio: Ejercicio) {
        // Calcular el número del ejercicio (actual + 1) para mostrar en el título
        val e = numeroEjercicio + 1
        titulo.text = "Ejercicio #$e"
        // Actualizar los campos de la interfaz con los datos del ejercicio
        nombreEjercicio.text = ejercicio.nombre
        descripcion.text = ejercicio.descripcion
        imagenEjercicio.setImageResource(obtenerImagen(ejercicio.animacion))

        // Convertir el tiempo del ejercicio (en segundos) a milisegundos
        val millis = (ejercicio.tiempo * 1000).toLong()
        // Cancelar cualquier temporizador anterior si existe
        if (timer != null) {
            timer!!.cancel()
        }
        // Actualizar el contador en la interfaz con el tiempo formateado
        texto_contador.text = convertMillisToMinutesSeconds(millis)

        // Configurar el botón para iniciar el temporizador cuando se haga clic
        empezarTiempo.setOnClickListener {
            iniciarTiempo(millis)
        }
    }

    fun iniciarTiempo(millis: Long) {
        // Crear un temporizador de cuenta regresiva, donde 'millis' es el tiempo total
        // y 1000 es el intervalo en milisegundos entre cada actualización (1 segundo).
        timer = object :
            CountDownTimer(millis, 1000) {
            // Este método se ejecuta cada vez que pasa un segundo.
            override fun onTick(millisUntilFinished: Long) {
                // Actualizar el contador en la interfaz con el tiempo restante formateado
                texto_contador.text = convertMillisToMinutesSeconds(millisUntilFinished)
            }

            // Este método se ejecuta cuando el temporizador llega a 0.
            override fun onFinish() {

            }
        }
        // Iniciar el temporizador
        timer!!.start()
    }

    fun convertMillisToMinutesSeconds(millis: Long): String {

        // Calcular los minutos. Primero, convertimos milisegundos a segundos, luego dividimos entre 60.
        val minutos = (millis / 1000) / 60
        // Calcular los segundos restantes. Usamos el operador módulo para obtener el resto de los segundos después de los minutos.
        val segundos = (millis / 1000) % 60
        // Formatear y devolver el tiempo en formato "mm:ss", asegurándonos de que siempre tenga 2 dígitos.
        return String.format("%02d:%02d", minutos, segundos)
    }


    fun convertirTiempo(tiempo: Int): String {
        // Convertir tiempo (en segundos) a minutos y segundos
        val minutos = tiempo / 60
        // Retornar el tiempo en formato "minutos:segundos", asegurándonos de que los segundos tengan dos dígitos
        return "$minutos:00"
    }

    fun obtenerImagen(nombreArchivo: String): Int {
        // Obtener el ID del recurso de imagen basado en el nombre del archivo
        val iconResId =
            resources.getIdentifier(nombreArchivo, "drawable", requireContext().packageName)
        return iconResId
    }


    private fun readJsonFromRaw(context: Context, resourceId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return inflater.inflate(R.layout.fragment_vista_ejercicio, container, false)
    }
}

//se creo los const para que no se cambien los valores asignados
const val TIPO_EJERCICIO = "tipo_ejercicio"
const val YOGA = "yoga"
const val MEDITACION = "meditacion"
const val RESPIRACION_PROFUNDA = "respiracion_profunda"











