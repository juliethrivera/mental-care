package com.iegabrielamistral.mentalcare.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iegabrielamistral.mentalcare.R
import de.hdodenhof.circleimageview.CircleImageView

class AdapterFragment(val onAvatarSelected: OnAvatarSelected) : RecyclerView.Adapter<AdapterFragment.ViewHolder>() {

    val avatars = listOf(

        com.iegabrielamistral.mentalcare.R.drawable.avatar_nino1,
        com.iegabrielamistral.mentalcare.R.drawable.avatar_nino2,
        com.iegabrielamistral.mentalcare.R.drawable.avatar_nino3,
        com.iegabrielamistral.mentalcare.R.drawable.avatar_nino4,
        com.iegabrielamistral.mentalcare.R.drawable.avatar_nino5,
        com.iegabrielamistral.mentalcare.R.drawable.avatar_nina1,
        com.iegabrielamistral.mentalcare.R.drawable.avatar_nina2,
        com.iegabrielamistral.mentalcare.R.drawable.avatar_nina3,
        com.iegabrielamistral.mentalcare.R.drawable.avatar_nina4,
        com.iegabrielamistral.mentalcare.R.drawable.avatar_nina5,


        )

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileAvatar: CircleImageView = itemView.findViewById(R.id.profileAvatar)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
            return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return avatars.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.profileAvatar.setImageResource(avatars[position])

        holder.profileAvatar.setOnClickListener {
            onAvatarSelected.onAvatarClick(avatars[position])
        }
    }
}


