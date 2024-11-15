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

    //declare las variables
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

    //el valor de la pregunta empieza en 0
    var question: Int = 0

    //se creo una lista
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


        //se inicializo las variables
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
        // Convertir el JSON a un objeto TestMental
        val testMental: TestMental =
            gson.fromJson(jsonString, object : TypeToken<TestMental>() {}.type)
        // Llamar a la función para cargar las preguntas
        cargarPregunta(testMental)


        siguiente.setOnClickListener {
            // Primero, obtenemos la selección actual
            obtenerSeleccion()
            // Incrementamos la variable 'question' para pasar a la siguiente pregunta
            question++
            // Comprobamos si aún hay más preguntas (hasta la pregunta 19)
            if (question < 20) {
                // Cambiamos el texto del botón a "Finalizar" si estamos en la última pregunta (question == 19)
                siguiente.text = if(question == 19) "Finalizar" else "Siguiente"
                // Llamamos a cargarPregunta() para cargar la siguiente pregunta
                cargarPregunta(testMental)
            } else {
                // Si hemos llegado a la última pregunta, guardamos los resultados
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                sharedPref?.apply {
                    edit()?.putInt(SAVED_RESULTADO_1,selecciones[0])?.apply()
                    edit()?.putInt(SAVED_RESULTADO_2,selecciones[1])?.apply()
                    edit()?.putInt(SAVED_RESULTADO_3,selecciones[2])?.apply()
                    edit()?.putInt(SAVED_RESULTADO_4,selecciones[3])?.apply()
                    edit()?.putInt(SAVED_RESULTADO_5,selecciones[4])?.apply()
                }
                // Ahora que hemos guardado los resultados, vamos a mostrar el fragmento de resultados
                val resultadosFragment = ResultadosFragment(selecciones)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, resultadosFragment).commit()

            }
        }
        // Deshabilitamos el botón "Siguiente" para evitar múltiples clics antes de que se procese la acción
        siguiente.isEnabled = false


        // Establecemos un listener para detectar cambios de selección en el RadioGroup 'opciones'
        opciones.setOnCheckedChangeListener { group, checkedId ->
            // 'checkedId' es el ID de la opción seleccionada dentro del RadioGroup.
            // Si no hay ninguna opción seleccionada, checkedId será -1.

            // Habilitamos el botón 'Siguiente' solo si se ha seleccionado una opción
            siguiente.isEnabled = checkedId != -1

        }
    }

    private fun readJsonFromRaw(requireContext: Context, preguntas: Int): String {
        val inputStream: InputStream = requireContext.resources.openRawResource(preguntas)
        return inputStream.bufferedReader().use { it.readText() }
    }


    fun obtenerSeleccion() {
        // Log de depuración para mostrar la pregunta actual (index)
        Log.e("TestMental", "question: $question")
        // Determinamos la categoría de la pregunta actual en función de su número (question)
        val categoria = if (question >= 0 && question <= 3) {
            0  // Categoría 0 para preguntas entre 0 y 3
        } else if (question >= 4 && question <= 7) {
            1// Categoría 1 para preguntas entre 4 y 7
        } else if (question >= 8 && question <= 11) {
            2 // Categoría 2 para preguntas entre 8 y 11
        } else if (question >= 12 && question <= 15) {
            3 // Categoría 3 para preguntas entre 12 y 15
        } else {
            4 // Categoría 4 para preguntas mayores a 15
        }

        // Log de advertencia para mostrar la categoría asignada
        Log.w("TestMental", "categoria: $categoria")

        // Dependiendo de la opción seleccionada, sumamos diferentes puntos a la categoría correspondiente
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

        // Log de depuración para mostrar los puntos acumulados en la categoría correspondiente
        Log.d("TestMental", "selecciones: ${selecciones[categoria]}")
    }

    // Función que carga la pregunta actual en la interfaz
    fun cargarPregunta(testMental: TestMental) {

        // Limpiamos cualquier selección previa en el RadioGroup (opciones)
        opciones.clearCheck()

        // Limpiamos cualquier selección previa en el RadioGroup (opciones)
        val p = question + 1
        // Actualizamos el progreso de la barra de progreso
        actualizarProgreso(p)

        // Actualizamos el texto que muestra el número de la pregunta y el total (por ejemplo, "1/20")
        cantidadPreguntas.text = "$p/20"

        // Actualizamos el texto de la pregunta y las opciones de respuesta
        pregunta.text = testMental.preguntas[question].pregunta
        opcion_1.text = testMental.preguntas[question].opcion1
        opcion_2.text = testMental.preguntas[question].opcion2
        opcion_3.text = testMental.preguntas[question].opcion3
        opcion_4.text = testMental.preguntas[question].opcion4
        opcion_5.text = testMental.preguntas[question].opcion5
    }
    // Función que actualiza el progreso en la barra de progreso
    fun actualizarProgreso(pregunta: Int) {
        // Calculamos el progreso como un valor entre 0 y 100
        val p = 5 * pregunta
        // Actualizamos la barra de progreso con el valor calculado
        progreso.progress = p
    }
}

//se creo un const para no cambiar los valores
const val SAVED_RESULTADO_1 = "saved_resultado_1"
const val SAVED_RESULTADO_2 = "saved_resultado_2"
const val SAVED_RESULTADO_3 = "saved_resultado_3"
const val SAVED_RESULTADO_4 = "saved_resultado_4"
const val SAVED_RESULTADO_5 = "saved_resultado_5"




