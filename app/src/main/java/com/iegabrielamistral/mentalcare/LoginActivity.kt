package com.iegabrielamistral.mentalcare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.iegabrielamistral.mentalcare.usuario.RegistroFragment

class LoginActivity : AppCompatActivity() {

    // [START declare_auth]
    //firebase
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    private lateinit var googleSignInClient: GoogleSignInClient


    //
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }

    // lo que hay en este codigo es para iniciar sesión co google y para recuperar contraseña
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // [START config_signin]
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            //.requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        // [END config_signin]

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // [END initialize_auth]

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                //Log.d(RegistroFragment.TAG, "firebaseAuthWithGoogle:" + account.id)
                account?.let {
                    account.idToken?.let { id ->
                        firebaseAuthWithGoogle(id)
                    }
                }

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                //Log.w(RegistroFragment.TAG, "Google sign in failed", e)
            }
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser

                    // Obtiene la referencia a la base de datos en tiempo real de Firebase.
                    val database = FirebaseDatabase.getInstance().reference
                    // Obtiene las preferencias compartidas (SharedPreferences) usando el nombre del paquete.
                    val sharePref = getSharedPreferences(packageName, MODE_PRIVATE)
                    // Recupera los datos almacenados en las preferencias compartidas: nombre, apellido, fecha y correo.
                    val nombre = sharePref.getString("nombre", "")
                    val apellido = sharePref.getString("apellido", "")
                    val fecha = sharePref.getString("fecha", "")
                    val correo = sharePref.getString("correo", "")
                    // Crea un objeto Usuario con los datos recuperados de las preferencias compartidas.
                    // Utiliza el operador !! para asegurar que los valores no sean nulos (aunque debería manejarse con más cuidado).
                    val usuario = Usuario(
                        nombre!!,    // Asegura que 'nombre' no sea nulo
                        apellido!!,  // Asegura que 'apellido' no sea nulo
                        fecha!!,     // Asegura que 'fecha' no sea nulo
                        correo!!     // Asegura que 'correo' no sea nulo
                    )
                    // Obtiene el ID de usuario (uid) del objeto `user`, que podría ser un usuario autenticado en Firebase.
                    val userId = user?.uid
                    // Si el userId no es nulo, ejecuta el bloque de código dentro de `let`.
                    userId?.let {
                        // Guarda el objeto `usuario` en la base de datos de Firebase en la ruta "Usuarios/{userId}".
                        // Utiliza el `uid` del usuario como el identificador único en la base de datos.
                        database.child("Usuarios").child(it).setValue(usuario)
                    }

                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    // Esta funcion es para restablecer la contraseña
    fun passwordRecover(email: String) {
        Log.d("FirebaseAuth", "email: $email")
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseAuth", "isSuccessful")
                    Toast.makeText(this, "Se ha enviado un mensaje al correo", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Log.d("FirebaseAuth", "error: ${task.exception?.message}")
                    Toast.makeText(this, "Error ${task.exception?.message}  ", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(RegistroFragment.TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(RegistroFragment.TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
    // [END auth_with_google]

    // [START signin]
    fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signin]

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

private const val TAG = "GoogleActivity"
private const val RC_SIGN_IN = 9001