package com.iegabrielamistral.mentalcare.usuario

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.iegabrielamistral.mentalcare.R

class inicioFragment : Fragment() {

    companion object {
        fun newInstance() = inicioFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textV : TextView = view.findViewById(R.id.textV)
        val text : TextView = view.findViewById(R.id.text)
        val button : Button = view.findViewById(R.id.button)
        val button2 : Button = view.findViewById(R.id.button2)

        text.setOnClickListener {

        }
        textV.setOnClickListener {

        }
        button.setOnClickListener {
            val registroFragment = RegistroFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, registroFragment).commit()
        }
        button2.setOnClickListener {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }


}