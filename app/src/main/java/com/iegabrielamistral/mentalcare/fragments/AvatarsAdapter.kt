package com.iegabrielamistral.mentalcare.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AvatarsAdapter(val onAvactarSelected: OnAvactarSelected) : RecyclerView.Adapter<AvatarsAdapter.ViewHolder>() {

    val avatars = listOf(

        R.drawable.avatar_nino1


    )

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileAvatar: CircleImageView = itemView.findViewById(R.id.profile_Image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
            return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return avatars.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.profileAvatar.setImageResource(avatars[position])

        holder.profileAvatar.set
    }
}
















}