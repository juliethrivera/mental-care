package com.iegabrielamistral.mentalcare.fragments

import android.content.Context
import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.Usuario
import de.hdodenhof.circleimageview.CircleImageView

class PerfilUsuarioFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    lateinit var usuario: CircleImageView
    lateinit var cerrarSesion: TextView
    lateinit var resultado: TextView
    lateinit var edit: ImageView
    lateinit var nbr: TextView
    lateinit var apd: TextView
    lateinit var electronico: TextView
    lateinit var nacimiento: TextView


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



        usuario = view.findViewById(R.id.usuario)
        cerrarSesion = view.findViewById(R.id.cerrarSesion)
        resultado = view.findViewById(R.id.resultado)
        edit = view.findViewById(R.id.edit)
        nbr = view.findViewById(R.id.nombre_1)
        apd = view.findViewById(R.id.apellido_1)
        electronico = view.findViewById(R.id.correo_electronico)
        nacimiento = view.findViewById(R.id.fecha_nacimiento)





        cerrarSesion.setOnClickListener {
            signOut()

        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            //.requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        auth = Firebase.auth


        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        var avatar = sharedPref?.getInt(SAVED_AVATAR_PROFILE, 0)

        sharedPref?.apply {

            val lista = listOf(
                getInt(SAVED_RESULTADO_1, 0), getInt(SAVED_RESULTADO_2, 0), getInt(
                    SAVED_RESULTADO_3, 0
                ), getInt(SAVED_RESULTADO_4, 0), getInt(SAVED_RESULTADO_5, 0)
            )

            resultado.setOnClickListener {
                val resultadosFragment = ResultadosFragment(lista)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, resultadosFragment).commit()

            }
        }


        val avatars = listOf(
            R.drawable.foto_perfil,
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


        if (avatar!! >= avatars.size) {
            avatar = 0
        }


        usuario.setImageResource(avatars[avatar])


        edit.setOnClickListener {
            val avatarsFragment = AvatarsFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, avatarsFragment).commit()


        }
////
        val database = FirebaseDatabase.getInstance().reference

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        userId?.let {
            database.child("Usuarios").child(it).addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val usuario = snapshot.getValue(Usuario::class.java)
                    usuario?.let {

                        nbr.text = it.nombre
                        apd.text = it.apellido
                        nacimiento.text = it.fecha
                        electronico.text = it.correo
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }


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

const val SAVED_AVATAR_PROFILE = "saved_avatar_profile"
