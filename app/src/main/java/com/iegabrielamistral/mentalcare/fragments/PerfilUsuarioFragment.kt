package com.iegabrielamistral.mentalcare.fragments

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.R
import de.hdodenhof.circleimageview.CircleImageView

class PerfilUsuarioFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    lateinit var nombre_del_usuario : TextView
    lateinit var cerrar_sesion : ImageView
    lateinit var edit : ImageView
    lateinit var resultado : Button
    lateinit var usuario : CircleImageView


    companion object {
        fun newInstance() = PerfilUsuarioFragment()
    }

    private val viewModel: PerfilUsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_perfil_usuario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        edit = view.findViewById(R.id.Edit)
        nombre_del_usuario = view.findViewById(R.id.nombre_del_usuario)
        resultado = view.findViewById(R.id.resultado)
        cerrar_sesion = view.findViewById(R.id.cerrar_sesion)



        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
         //   .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        auth = Firebase.auth

    }
    private fun signOut() {
        // [START auth_sign_out]
        auth.signOut()
        googleSignInClient.signOut()
        // [END auth_sign_out]
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }



}