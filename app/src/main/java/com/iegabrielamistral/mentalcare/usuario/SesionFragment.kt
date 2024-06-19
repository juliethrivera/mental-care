package com.iegabrielamistral.mentalcare.usuario

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iegabrielamistral.mentalcare.R

class SesionFragment : Fragment() {

    companion object {
        fun newInstance() = SesionFragment()
    }

    private val viewModel: SesionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_sesion, container, false)
    }
}