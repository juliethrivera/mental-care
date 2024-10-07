package com.iegabrielamistral.mentalcare.usuario


import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.MainActivity
import com.iegabrielamistral.mentalcare.R
import com.iegabrielamistral.mentalcare.fragments.HomeFragment

class RegistroFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val iniciar: Button = view.findViewById(R.id.iniciar)
        val crear: Button = view.findViewById(R.id.crear)
        // val button: SignInButton = view.findViewById(R.id.button)


        iniciar.setOnClickListener {
            val inicioFragment = inicioFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, inicioFragment).commit()
        }
        crear.setOnClickListener {
            val cuentaFragment = cuentaFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, cuentaFragment).commit()
        }
        /* button.setOnClickListener {
             (requireActivity() as LoginActivity).apply {
                 signIn()
             }*/
    }
}

// [START on_start_check_user]


// [END on_start_check_user]

// [START onactivityresult]

// [END onactivityresult]

// [START auth_with_google]

/*object {
    private const val TAG = "GoogleActivity"

    fun newInstance() = RegistroFragment()
}
*/


