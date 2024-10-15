package com.iegabrielamistral.mentalcare.usuario

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.MainActivity
import com.iegabrielamistral.mentalcare.R

class RestablecerFragment : Fragment() {
    lateinit var ediTxtEmail: EditText
    lateinit var btnResetPassaword: Button
    lateinit var  regresar : Button
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_restablecer, container, false)
    }

    companion object {
        fun newInstance() = RestablecerFragment()
    }


    private val viewModel: RestablecerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ediTxtEmail = view.findViewById(R.id.ediTxtEmail)
        btnResetPassaword = view.findViewById(R.id.btnResetPassaword)
        regresar = view.findViewById(R.id.back_regresar)


        auth = Firebase.auth

        regresar.setOnClickListener {
            val vistaFragment = VistaFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2,vistaFragment).commit()

        }


        btnResetPassaword.setOnClickListener {

            (requireActivity() as LoginActivity).apply{

                passwordRecover(ediTxtEmail.text.toString())
            }
        }
    }

}