package com.iegabrielamistral.mentalcare.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iegabrielamistral.mentalcare.R
import de.hdodenhof.circleimageview.CircleImageView
// Adaptador para manejar la lista de avatares en un RecyclerView
class AvatarsAdapter(val onAvatarSelected: OnAvatarSelected) :
    RecyclerView.Adapter<AvatarsAdapter.ViewHolder>() {

    // Lista de recursos de imagen para los avatares. Cada entrada es un recurso drawable.
    val avatars = listOf(

        R.drawable.foto_perfil, //avatar predeterminsdo
        R.drawable.avatar_nino1,//avatars de niños
        R.drawable.avatar_nino2,
        R.drawable.avatar_nino3,
        R.drawable.avatar_nino4,
        R.drawable.avatar_nino5,
        R.drawable.avatar_nina1,//avatars de niñas
        R.drawable.avatar_nina2,
        R.drawable.avatar_nina3,
        R.drawable.avatar_nina4,
        R.drawable.avatar_nina5,


        )

    // Vista de cada elemento en el RecyclerView. En este caso, un avatar de perfil.
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Definición del ImageView donde se mostrará el avatar. Usamos CircleImageView para mostrar las imágenes en forma de circulo
        val profileAvatar: CircleImageView = itemView.findViewById(R.id.profileAvatar)

    }

    // Este método crea una nueva vista para un elemento del RecyclerView.
    // Se llama cuando el RecyclerView necesita crear una nueva vista para un ítem.
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla el diseño del item (layout de cada avatar) y lo devuelve como ViewHolder.
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        return ViewHolder(itemView) // Retorna el ViewHolder para el nuevo ítem
    }


    // Este método devuelve el número de ítems que hay en el RecyclerView (en este caso, la cantidad de avatares).
    override fun getItemCount(): Int {
        return avatars.size
    }


    // Este método es responsable de actualizar la vista de un ítem en el RecyclerView con los datos correspondientes.
    // En este caso, asigna la imagen del avatar al ImageView de cada ítem.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.profileAvatar.setImageResource(avatars[position])

        // Configura un OnClickListener en la imagen del avatar
        holder.profileAvatar.setOnClickListener {
            // Llama al método onAvatarClick cuando un avatar es seleccionado
            // Se pasa la posición del avatar seleccionado (que puede usarse como el identificador)
            onAvatarSelected.onAvatarClick(position)
        }
    }
}


