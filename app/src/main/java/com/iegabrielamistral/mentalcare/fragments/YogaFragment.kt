package com.iegabrielamistral.mentalcare.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.iegabrielamistral.mentalcare.R

class YogaFragment : Fragment() {

    companion object {
        fun newInstance() = YogaFragment()
    }

    private val viewModel: YogaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_yoga, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //declare y inicialice las variables
        val atras: ImageView = view.findViewById(R.id.atras)
        val empezar: Button = view.findViewById(R.id.empezar)

        //se establece un listener para el click en el bottom
        atras.setOnClickListener {
            //creamos una instancia del fragment ejerciciosRelajacionFragment
            val ejerciciosRelajacionFragment = EjerciciosRelajacionFragment()
            //iniciamos una transaccion de fragmentos
            requireActivity().supportFragmentManager.beginTransaction()
                //reemplazamos el fragmento actual con el nuevo fragmento(ejerciciosRelajacion)
                .replace(R.id.fragmentContainerView, ejerciciosRelajacionFragment).commit()
        }

        // Se establece un listener para el clic en el botón 'empezar'
        empezar.setOnClickListener {
            // Creamos un objeto Bundle para pasar datos al fragmento destino
            val bundle: Bundle = Bundle()
            // Agregamos un valor tipo String al Bundle, asociando una clave y un valor
            bundle.putString(TIPO_EJERCICIO, YOGA)
            // Creamos una nueva instancia del fragmento VistaEjercicioFragment
            val vistaEjercicioFragment = VistaEjercicioFragment()
            // Asignamos el Bundle de datos al fragmento para que pueda acceder a ellos
            vistaEjercicioFragment.arguments = bundle
            // Iniciamos la transacción de fragmentos
            requireActivity().supportFragmentManager.beginTransaction()
                // Reemplazamos el fragmento actual con el nuevo fragmento VistaEjercicioFragment
                .replace(R.id.fragmentContainerView, vistaEjercicioFragment).commit()

        }


    }
}

