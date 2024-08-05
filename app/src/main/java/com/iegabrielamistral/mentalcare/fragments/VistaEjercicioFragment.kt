package com.iegabrielamistral.mentalcare.fragments

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
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
import com.iegabrielamistral.mentalcare.model.Ejercicio
import com.iegabrielamistral.mentalcare.model.`EjerciciosRelajacion`
import org.json.JSONObject
import java.io.InputStream

class VistaEjercicioFragment: Fragment() {

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

    var timer: CountDownTimer? = null

    var numeroEjercicio = 0

    companion object {
        fun newInstance() = VistaEjercicioFragment()
    }

    private val viewModel: VistaEjercicioViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        anterior = view.findViewById(R.id.anterior)
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


        anterior.setOnClickListener {
            val yogaFragment = YogaFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, yogaFragment).commit()


        }

        val tipoEjercicio = requireArguments().getString(TIPO_EJERCICIO, YOGA)

        val jsonString = when(tipoEjercicio){
            YOGA ->{
                 readJsonFromRaw(requireContext(), R.raw.yoga)
            }
            MEDITACION ->{
                readJsonFromRaw(requireContext(), R.raw.meditacion)
            }
            else ->{
                readJsonFromRaw(requireContext(), R.raw.respiracion_profunda)
            }

        }

        var jsonObject = JSONObject(jsonString)

        val gson = Gson()

        val data: `EjerciciosRelajacion` =
            gson.fromJson(jsonString, object : TypeToken<`EjerciciosRelajacion`>() {}.type)

        val ejercicios = data.ejercicios


        cargarSiguienteEjercicio(ejercicios[numeroEjercicio])

        siguienteEjercicio.setOnClickListener {
            numeroEjercicio++
            cargarSiguienteEjercicio(ejercicios[numeroEjercicio])
        }

    }

    fun cargarSiguienteEjercicio(ejercicio: Ejercicio) {
        val e = numeroEjercicio + 1
        titulo.text = "Ejercicio #$e"
        nombreEjercicio.text = ejercicio.nombre
        descripcion.text = ejercicio.descripcion
        imagenEjercicio.setImageResource(obtenerImagen(ejercicio.animacion))

        val millis = (ejercicio.tiempo * 1000).toLong()

        if (timer != null) {
            timer!!.cancel()
        }

        texto_contador.text = convertMillisToMinutesSeconds(millis)

        empezarTiempo.setOnClickListener {
            iniciarTiempo(millis)
        }
    }

    fun iniciarTiempo(millis: Long) {
        timer = object :
            CountDownTimer(millis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                texto_contador.text = convertMillisToMinutesSeconds(millisUntilFinished)
            }


            override fun onFinish() {

            }
        }
        timer!!.start()
    }

    fun convertMillisToMinutesSeconds(millis: Long): String {
        val minutos = (millis / 1000) / 60
        val segundos = (millis / 1000) % 60

        return String.format("%02d:%02d", minutos, segundos)
    }


    fun convertirTiempo(tiempo: Int): String {
        val minutos = tiempo / 60
        return "$minutos:00"
    }

    fun obtenerImagen(nombreArchivo: String): Int {
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

const val TIPO_EJERCICIO = "tipo_ejercicio"
const val YOGA = "yoga"
const val MEDITACION = "meditacion"
const val RESPIRACION_PROFUNDA = "respiracion_profunda"











