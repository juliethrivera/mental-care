package com.iegabrielamistral.mentalcare.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iegabrielamistral.mentalcare.R
import de.hdodenhof.circleimageview.CircleImageView

class AvatarsAdapter(val onAvatarSelected: OnAvatarSelected) :
    RecyclerView.Adapter<AvatarsAdapter.ViewHolder>() {

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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileAvatar: CircleImageView = itemView.findViewById(R.id.profileAvatar)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return avatars.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.profileAvatar.setImageResource(avatars[position])

        holder.profileAvatar.setOnClickListener {
            onAvatarSelected.onAvatarClick(position)
        }
    }
}


