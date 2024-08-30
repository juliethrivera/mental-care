package com.iegabrielamistral.mentalcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iegabrielamistral.mentalcare.R

class BlankViewModel : Fragment() {

    companion object {
        fun newInstance() = BlankViewModel()
    }

    private fun findViewById(bnvMenu: Int): Any {
        TODO("Not yet implemented")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }
}