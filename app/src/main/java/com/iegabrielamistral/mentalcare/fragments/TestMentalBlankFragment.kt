package com.iegabrielamistral.mentalcare.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.model.TestMental
import java.io.InputStream


class TestMentalBlankFragment : Fragment() {

    private lateinit var anterior: ImageView
    private lateinit var testMental: TextView
    private lateinit var progreso: ProgressBar
    private lateinit var cantidadPreguntas: TextView
    private lateinit var pregunta: TextView
    private lateinit var opciones: RadioGroup
    private lateinit var opcion_1: RadioButton
    private lateinit var opcion_2: RadioButton
    private lateinit var opcion_3: RadioButton
    private lateinit var opcion_4: RadioButton
    private lateinit var opcion_5: RadioButton
    private lateinit var siguiente: Button

    var question: Int = 0

    val selecciones = mutableListOf(0, 0, 0, 0, 0)

    companion object {
        fun newInstance() = TestMentalBlankFragment()
    }

    private val viewModel: TestMentalBlankViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_test_mental_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        anterior = view.findViewById(R.id.home)
        testMental = view.findViewById(R.id.testMental)
        progreso = view.findViewById(R.id.progreso)
        cantidadPreguntas = view.findViewById(R.id.cantidadPreguntas)
        pregunta = view.findViewById(R.id.pregunta)
        opciones = view.findViewById(R.id.opciones)
        opcion_1 = view.findViewById(R.id.opcion_1)
        opcion_2 = view.findViewById(R.id.opcion_2)
        opcion_3 = view.findViewById(R.id.opcion_3)
        opcion_4 = view.findViewById(R.id.opcion_4)
        opcion_5 = view.findViewById(R.id.opcion_5)
        siguiente = view.findViewById(R.id.Siguiente)


        val jsonString = readJsonFromRaw(requireContext(), R.raw.preguntas)

        val gson = Gson()

        val testMental: TestMental =
            gson.fromJson(jsonString, object : TypeToken<TestMental>() {}.type)

        cargarPregunta(testMental)

        siguiente.setOnClickListener {
            obtenerSeleccion()
            question++
            if (question < 20) {

                cargarPregunta(testMental)
            } else {
                val resultadosFragment = ResultadosFragment(selecciones)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, resultadosFragment).commit()

            }
        }
        siguiente.isEnabled = false



        opciones.setOnCheckedChangeListener { group, checkedId ->
            siguiente.isEnabled = checkedId != -1

        }
    }

    private fun readJsonFromRaw(requireContext: Context, preguntas: Int): String {
        val inputStream: InputStream = requireContext.resources.openRawResource(preguntas)
        return inputStream.bufferedReader().use { it.readText() }
    }


    fun obtenerSeleccion() {

        Log.e("TestMental", "question: $question")

        val categoria = if (question >= 0 && question <= 3) {
            0
        } else if (question >= 4 && question <= 7) {
            1
        } else if (question >= 8 && question <= 11) {
            2
        } else if (question >= 12 && question <= 15) {
            3
        } else {
            4
        }

        Log.w("TestMental", "categoria: $categoria")

        when (opciones.checkedRadioButtonId) {
            opcion_1.id -> {
                selecciones[categoria] += 0

            }

            opcion_2.id -> {
                selecciones[categoria] += 10

            }

            opcion_3.id -> {
                selecciones[categoria] += 15
            }

            opcion_4.id -> {
                selecciones[categoria] += 20
            }

            opcion_5.id -> {
                selecciones[categoria] += 25
            }
        }

        Log.d("TestMental", "selecciones: ${selecciones[categoria]}")
    }

    fun cargarPregunta(testMental: TestMental) {

        opciones.clearCheck()

        val p = question + 1
        actualizarProgreso(p)

        cantidadPreguntas.text = "$p/20"

        pregunta.text = testMental.preguntas[question].pregunta
        opcion_1.text = testMental.preguntas[question].opcion1
        opcion_2.text = testMental.preguntas[question].opcion2
        opcion_3.text = testMental.preguntas[question].opcion3
        opcion_4.text = testMental.preguntas[question].opcion4
        opcion_5.text = testMental.preguntas[question].opcion5

    }

    fun actualizarProgreso(pregunta: Int) {
        val p = 5 * pregunta
        progreso.progress = p
    }


}
