package com.iegabrielamistral.mentalcare.meditacion

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iegabrielamistral.mentalcare.R

class MeditacionFragment : Fragment() {

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
}