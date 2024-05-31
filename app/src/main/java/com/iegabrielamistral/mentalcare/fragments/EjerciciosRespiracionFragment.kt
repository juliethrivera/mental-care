package com.iegabrielamistral.mentalcare.fragments

import androidx.fragment.app.viewModels 
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iegabrielamistral.mentalcare.R
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class EjerciciosRespiracionFragment : Fragment() {

    companion object {
        fun newInstance() = EjerciciosRespiracionFragment()
    }

    private val viewModel: EjerciciosRespiracionViewModel by viewModels()

    private fun viewModels() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_ejercicios_respiracion, container, false)
    }
}

private operator fun Unit.getValue(
    ejerciciosRespiracionFragment: EjerciciosRespiracionFragment,
    property: KProperty<*>
): EjerciciosRespiracionViewModel {
    TODO("Not yet implemented")
}
