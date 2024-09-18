package com.iegabrielamistral.mentalcare.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iegabrielamistral.mentalcare.R

class AvatarFragment(val onAvatarSelected: OnAvatarSelected) : BottomSheetDialogFragment(){

    lateinit var constraint : ConstraintLayout
    lateinit var listaDeAvatars : RecyclerView

    var avatarsAdapter : AvatarsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_avatar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getSharedPreferences(context.MODE_PRIVATE)

        constraint = view.findViewById(R.id.constraint)
        listaDeAvatars = view.findViewById(R.id.listaDeAvatars)

        avatarsAdapter = AvatarAdapter(onAvatarSelected)


        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

        listaDeAvatars.apply {
            layoutManager = gridLayoutManager
            adapter = avatarsAdapter
        }

    }

}

const val SAVED_AVATAR_PROFILE = "saved_avatar_profile"