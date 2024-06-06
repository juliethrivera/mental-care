package com.iegabrielamistral.mentalcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
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




        horribleImage.setOnClickListener{
            mostrarMensajes("Julieth no esta estudiando")
        }
        malImage.setOnClickListener{

        }
        aburridoImage.setOnClickListener{

        }
        bienImage.setOnClickListener{

        }
        excelenteImage.setOnClickListener{

        }

    }

    fun mostrarMensajes(mensaje : String){
        val mensajeBottomDialog = MensajeBottomDialog()
        mensajeBottomDialog.arguments?.putString(MENSAJE, mensaje)
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