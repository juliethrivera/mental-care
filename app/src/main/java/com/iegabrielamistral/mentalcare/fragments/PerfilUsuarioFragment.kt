package com.iegabrielamistral.mentalcare.fragments

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.RestrictionEntry.TYPE_NULL
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.compose.material.icons.materialIcon
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.MainActivity
import com.iegabrielamistral.mentalcare.R
import de.hdodenhof.circleimageview.CircleImageView

class PerfilUsuarioFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    lateinit var edit: ImageView
    lateinit var usuario : CircleImageView
    lateinit var cerrar_sesion : ImageView
    lateinit var nombre_usuario : EditText



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
        usuario = view.findViewById(R.id.usuario)
        cerrar_sesion = view.findViewById(R.id.cerrar_sesion)
        nombre_usuario = view.findViewById(R.id.nombre_usuario)
        nombre_usuario.isEnabled = false
        nombre_usuario.isFocusable = false


        cerrar_sesion.setOnClickListener {

            signOut()

        }

        edit.setOnClickListener {
            nombre_usuario.isEnabled = true
            nombre_usuario.isFocusable = true


        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            //.requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        auth = Firebase.auth



        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

       val avatar = sharedPref?.getInt(SAVED_AVATAR_PROFILE, 1)

        val avatars = listOf(

            R.drawable.avatar_nino1,
            R.drawable.avatar_nino2,
            R.drawable.avatar_nino3,
            R.drawable.avatar_nino4,
            R.drawable.avatar_nino5,
            R.drawable.avatar_nina1,
            R.drawable.avatar_nina2,
            R.drawable.avatar_nina3,
            R.drawable.avatar_nina4,
            R.drawable.avatar_nina5,

            )

        usuario.setImageResource(avatars[avatar!!])

        usuario.setOnClickListener {


        }

    }

    private fun extracted() {
        nombre_usuario
    }


    private fun signOut() {
        // [START auth_sign_out]
        auth.signOut()
        googleSignInClient.signOut()
        // [END auth_sign_out]
        val intent = Intent(requireActivity(),LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }



}


const val SAVED_AVATAR_PROFILE= "saved_avatar_profile"