package com.iegabrielamistral.mentalcare.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iegabrielamistral.mentalcare.R

class AvatarsFragment : Fragment(), OnAvatarSelected {

    //se declaro las variables
    lateinit var listaAvatars: RecyclerView
    lateinit var anterior: ImageView
    lateinit var avatarsAdapter: AvatarsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_avatars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //se inicializo las variables
        listaAvatars = view.findViewById(R.id.listaDeAvatars)
        anterior = view.findViewById(R.id.anterior)
        avatarsAdapter = AvatarsAdapter(this)

        // Inicializa un GridLayoutManager para organizar los elementos en una cuadrícula con 2 columnas
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)

        // Configura el RecyclerView 'listaAvatars' con el GridLayoutManager y el adaptador 'avatarsAdapter'
        listaAvatars.apply {
            layoutManager = gridLayoutManager  // Establece el gestor de diseño a un GridLayout con 2 columnas
            adapter = avatarsAdapter  // Establece el adaptador que proporciona los datos de los avatares
        }

        // Configura el evento de clic para el botón 'anterior', que navega al fragmento de perfil de usuario
        anterior.setOnClickListener {
            // Crea una nueva instancia del fragmento de perfil de usuario
            val perfilUsuarioFragment = PerfilUsuarioFragment()
            // Reemplaza el fragmento actual con 'PerfilUsuarioFragment' en el contenedor de fragmentos
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, perfilUsuarioFragment).commit()
        }
    }
    // Implementación de la interfaz 'OnAvatarSelected' que maneja la selección de un avatar
    override fun onAvatarClick(avatar: Int) {
        // Obtiene el contexto de la actividad y accede a las preferencias compartidas
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        // Guarda el avatar seleccionado en las preferencias compartidas usando el identificador del avatar
        sharedPref?.edit()?.putInt(SAVED_AVATAR_PROFILE, avatar)?.apply()
        // Crea una nueva instancia del fragmento de perfil de usuario después de haber seleccionado un avatar
        val perfilUsuarioFragment = PerfilUsuarioFragment()
        // Reemplaza el fragmento actual con el fragmento de perfil de usuario
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, perfilUsuarioFragment).commit()
    }
}



