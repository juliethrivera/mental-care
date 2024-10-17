package com.iegabrielamistral.mentalcare.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iegabrielamistral.mentalcare.R

class AvatarsFragment : Fragment(), OnAvatarSelected {

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

        listaAvatars = view.findViewById(R.id.listaDeAvatars)
        anterior = view.findViewById(R.id.anterior)
        avatarsAdapter = AvatarsAdapter(this)

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)

        listaAvatars.apply {
            layoutManager = gridLayoutManager
            adapter = avatarsAdapter
        }

        anterior.setOnClickListener {
            val perfilUsuarioFragment = PerfilUsuarioFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, perfilUsuarioFragment).commit()
        }
    }

    override fun onAvatarClick(avatar: Int) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        sharedPref?.edit()?.putInt(SAVED_AVATAR_PROFILE, avatar)?.apply()
        val perfilUsuarioFragment = PerfilUsuarioFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, perfilUsuarioFragment).commit()
    }
}



