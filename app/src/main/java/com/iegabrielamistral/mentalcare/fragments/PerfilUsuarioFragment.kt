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


    //se declararon las variables
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

        //se inicializaron las variables
        usuario = view.findViewById(R.id.usuario)
        cerrarSesion = view.findViewById(R.id.cerrarSesion)
        resultado = view.findViewById(R.id.resultado)
        edit = view.findViewById(R.id.edit)
        nbr = view.findViewById(R.id.nombre_1)
        apd = view.findViewById(R.id.apellido_1)
        electronico = view.findViewById(R.id.correo_electronico)
        nacimiento = view.findViewById(R.id.fecha_nacimiento)


        //se creo un listener para presionar el boton y cerrar sesion
        cerrarSesion.setOnClickListener {
            signOut()

        }

        // Configura las opciones de inicio de sesión con Google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            //.requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Crea el cliente de Google Sign-In utilizando las opciones configuradas
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        // Inicializa la autenticación de Firebase
        auth = Firebase.auth

        // Accede a las preferencias compartidas de la actividad
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        // Obtiene el avatar guardado en las preferencias, por defecto será 0 si no existe
        var avatar = sharedPref?.getInt(SAVED_AVATAR_PROFILE, 0)

        // Accede a las preferencias y obtiene los resultados anteriores
        sharedPref?.apply {

            val lista = listOf(
                getInt(SAVED_RESULTADO_1, 0), getInt(SAVED_RESULTADO_2, 0), getInt(
                    SAVED_RESULTADO_3, 0
                ), getInt(SAVED_RESULTADO_4, 0), getInt(SAVED_RESULTADO_5, 0)
            )

            // Establece un evento de clic para el botón 'resultado'
            resultado.setOnClickListener {
                // Crea una nueva instancia de 'ResultadosFragment' con los resultados obtenidos
                val resultadosFragment = ResultadosFragment(lista)
                // Reemplaza el fragmento actual con 'ResultadosFragment'
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, resultadosFragment).commit()

            }
        }
        // Lista de recursos de imágenes de avatares disponibles
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

        // Verifica que el valor del avatar no exceda el tamaño de la lista de avatares
        if (avatar!! >= avatars.size) {
            // Si el valor de 'avatar' es mayor que el índice de la lista, se establece en 0 (avatar por defecto)
            avatar = 0
        }

        // Establece el avatar seleccionado en la vista de imagen del usuario
        usuario.setImageResource(avatars[avatar])

        // Configura un evento de clic para el botón 'edit', que abre un fragmento para editar el avatar
        edit.setOnClickListener {
            // Crea una instancia de 'AvatarsFragment'
            val avatarsFragment = AvatarsFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                // Reemplaza el fragmento actual con 'AvatarsFragment'
                .replace(R.id.fragmentContainerView, avatarsFragment).commit()


        }
        // Configura la base de datos de Firebase
        val database = FirebaseDatabase.getInstance().reference
        // Obtiene el ID de usuario de Firebase Authentication
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        // Si el ID de usuario es válido, recupera la información del usuario desde Firebase Realtime Database
        userId?.let {
            database.child("Usuarios").child(it).addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Obtiene el objeto 'Usuario' desde el snapshot de Firebase
                    val usuario = snapshot.getValue(Usuario::class.java)
                    usuario?.let {
                        // Actualiza los campos de la interfaz con la información del usuario obtenida desde la base de datos
                        nbr.text = it.nombre // Establece el nombre del usuario
                        apd.text = it.apellido // Establece el nombre del apellido
                        nacimiento.text = it.fecha // Establece la fecha de nacimiento del usuario
                         electronico.text = it.correo // Establece el correo del usuario
                    }
                }

                // Maneja cualquier error que ocurra durante la lectura de los datos
                override fun onCancelled(error: DatabaseError) {

                }

            })
        }


    }
    // Función para cerrar sesión
    private fun signOut() {
        // [START auth_sign_out]
        auth.signOut() // Cierra la sesión en Firebase Authentication
        googleSignInClient.signOut()
        // [END auth_sign_out]
        // Redirige al usuario a la pantalla de inicio de sesión
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()  // Finaliza la actividad actual para evitar que el usuario regrese con el botón 'Atrás'

    }

}
//se creo un const para no cambiar el valor asignado
const val SAVED_AVATAR_PROFILE = "saved_avatar_profile"
