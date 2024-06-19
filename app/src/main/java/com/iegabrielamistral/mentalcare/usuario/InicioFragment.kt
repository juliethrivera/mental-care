package com.iegabrielamistral.mentalcare.usuario

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.iegabrielamistral.mentalcare.R

class InicioFragment : Fragment() {

    companion object {
        fun newInstance() = InicioFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editT : EditText = view.findViewById(R.id.editT)
        val editText : EditText = view.findViewById(R.id.editText)
        val button : EditText = view.findViewById(R.id.button)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    private val viewModel: InicioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }


}