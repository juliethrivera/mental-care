package com.iegabrielamistral.mentalcare.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iegabrielamistral.mentalcare.R

class avatarsFragment(val onAvactarSelected: OnAvactarSelected) : BottomSheetDialogFragment(){

    lateinit var listaAvatars : RecyclerView

    var avatarsAdapter : AvatarsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_avatars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getSharedPreferences(Context.MODE_PRIVATE)

        listaAvatars = view.findViewById(R.id.listaAvatars)


        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

        listaAvatars.apply {
            layoutManager = gridLayoutManager
            adapter = avatarsAdapter
        }

    }

    }

    const val SAVED_AVATAR_PROFILE = "saved_avatar_profile"
