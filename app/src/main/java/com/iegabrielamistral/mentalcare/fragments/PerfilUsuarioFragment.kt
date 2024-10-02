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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.R

class PerfilUsuarioFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    lateinit var editaPerfil : Button
    lateinit var guardarCambios : Button
    lateinit var nombre_usuario : TextInputLayout
    lateinit var correo_electronico : TextInputLayout
    lateinit var contraseña : TextInputLayout
    lateinit var verInformacion : Button
    lateinit var fecha_de_nacimiento : TextInputLayout
    lateinit var edit: ImageView



    val correousuario: String = ""
    val nombreusuario: String = ""
    val contraseñausuario: String = ""
    val fechausuario: String = ""



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

       editaPerfil = view.findViewById(R.id.editaPerfil)
        guardarCambios = view.findViewById(R.id.guardarCambios)
        nombre_usuario = view.findViewById(R.id.textField1)
        correo_electronico = view.findViewById(R.id.textField2)
        contraseña = view.findViewById(R.id.textField3)
        verInformacion = view.findViewById(R.id.verInformacion)
        fecha_de_nacimiento = view.findViewById(R.id.textField4)
        edit = view.findViewById(R.id.Edit)
        val cerrarSesion: Button = view.findViewById(R.id.cerrarSesion)




        editaPerfil.setOnClickListener {
            correo_electronico.visibility = if(correo_electronico.visibility == View.VISIBLE){
                View.GONE
            }else{
                View.VISIBLE
            }
            contraseña.visibility = if(contraseña.visibility == View.VISIBLE){
                View.GONE
            }else{
                View.VISIBLE
            }
            nombre_usuario.visibility = if(nombre_usuario.visibility == View.VISIBLE){
                View.GONE
            }else{
                View.VISIBLE
            }


        }

        guardarCambios.setOnClickListener {

            correo_electronico.visibility = if(correo_electronico.visibility == View.VISIBLE){
                View.GONE
            }else{
                View.VISIBLE
            }
            contraseña.visibility = if(contraseña.visibility == View.VISIBLE){
                View.GONE
            }else{
                View.VISIBLE
            }
            nombre_usuario.visibility = if(nombre_usuario.visibility == View.VISIBLE){
                View.GONE
            }else{
                View.VISIBLE
            }


            guardarCambios()

        }

        verInformacion.setOnClickListener {


        }

        cerrarSesion.setOnClickListener {
            signOut()
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
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

    fun guardarCambios(){

        val sharedPreferences = requireContext().getSharedPreferences("PerfilUsuario",
            MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putString("nombre", nombreusuario)
        editor.putString("corre_electronico", contraseñausuario)
        editor.putString("contraseña", contraseñausuario)

        editor.apply()

    }

}