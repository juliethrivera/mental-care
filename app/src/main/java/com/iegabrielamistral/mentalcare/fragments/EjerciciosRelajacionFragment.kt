package com.iegabrielamistral.mentalcare.fragments

import android.app.Fragment
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iegabrielamistral.mentalcare.R


class EjerciciosRelajacionFragment : Fragment() {

    companion object {
        fun newInstance() = EjerciciosRelajacionFragment()
    }

    private val viewModel: EjerciciosRelajacionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)









    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_ejercicios_relajacion, container, false)
    }
}