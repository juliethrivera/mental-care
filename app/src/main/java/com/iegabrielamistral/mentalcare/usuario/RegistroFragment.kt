package com.iegabrielamistral.mentalcare.usuario


import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.gms.common.SignInButton
import com.iegabrielamistral.mentalcare.LoginActivity
import com.iegabrielamistral.mentalcare.R

class RegistroFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Aqui declare los componentes de la vista y los inicialise

        val iniciar: Button = view.findViewById(R.id.iniciar)
        val crear: Button = view.findViewById(R.id.crear)
        val button: SignInButton = view.findViewById(R.id.button)

     //esto es para iniciar sesión en la aplicación
        iniciar.setOnClickListener {
            val inicioFragment = InicioFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, inicioFragment).commit()
        }
        // esto es para crear una cuenta
        crear.setOnClickListener {
            val cuentaFragment = CuentaFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, cuentaFragment).commit()
        }
        //este es el boton de google
        button.setOnClickListener {
            (requireActivity() as LoginActivity).apply {
                signIn()
            }
        }
    }

    // [START on_start_check_user]


    // [END on_start_check_user]

    // [START onactivityresult]

    // [END onactivityresult]

    // [START auth_with_google]

    companion object {
        private const val TAG = "GoogleActivity"

        fun newInstance() = RegistroFragment()
    }

    private val viewModel: RegistroViewModel by viewModels()

}