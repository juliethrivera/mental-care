package com.iegabrielamistral.mentalcare.usuario

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.replace
import com.iegabrielamistral.mentalcare.R

class VistaFragment : Fragment() {

    companion object {
        fun newInstance() = VistaFragment()
    }

    private val viewModel: VistaViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.vista_fragment, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
        Handler().postDelayed({
            // Check if the fragment is still attached before proceeding
            if (isAdded && requireActivity().isFinishing.not()) {
                val registroFragment = RegistroFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView2, registroFragment).commit()
            }
        }, 2000)


    }

}