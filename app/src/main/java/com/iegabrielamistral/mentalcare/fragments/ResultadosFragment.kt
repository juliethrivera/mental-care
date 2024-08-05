package com.iegabrielamistral.mentalcare.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.iegabrielamistral.mentalcare.R

class ResultadosFragment : Fragment() {

    private lateinit var anterior: ImageView

    companion object {
        fun newInstance() = ResultadosFragment()
    }

    private val viewModel: ResultadosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_resultados, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val anterior : ImageView = view.findViewById(R.id.anterior)

        anterior.setOnClickListener {
            val testMentalBlankFragment = TestMentalBlankFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, testMentalBlankFragment).commit()

        }

    }
}