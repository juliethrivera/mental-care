package com.iegabrielamistral.mentalcare.meditacion

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.fragments.EjerciciosRelajacionFragment
import com.iegabrielamistral.mentalcare.fragments.VistaEjercicioFragment

class MeditacionFragment : Fragment() {

    private lateinit var atras : ImageView
    private lateinit var Button : Button

    companion object {
        fun newInstance() = MeditacionFragment()
    }

    private val viewModel: MeditacionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_meditacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val atras : ImageView = view.findViewById(R.id.atras)
        val button : Button = view.findViewById(R.id.button2)


        atras.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
            val ejerciciosRelajacionFragment = EjerciciosRelajacionFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ejerciciosRelajacionFragment).commit()
        }

    }

}