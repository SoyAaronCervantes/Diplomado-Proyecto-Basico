package com.soyaaroncervantes.platziconf.view.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.soyaaroncervantes.platziconf.R
import com.soyaaroncervantes.platziconf.model.Speaker

class SpeakerAdapter( val speakerListener: SpeakerListener ): RecyclerView.Adapter< SpeakerAdapter.ViewHolder >()  {
    private val speakers = arrayListOf<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder( LayoutInflater.from( parent.context ).inflate( R.layout.item_speaker, parent, false ) )

    override fun getItemCount(): Int = speakers.size

    override fun onBindViewHolder( viewHolder: ViewHolder, position: Int) {
        val speaker = speakers[ position ]
        viewHolder.textViewSpeakerJobTitle.text = speaker.jobTitle
        viewHolder.textViewSpeakerName.text = speaker.name
        Glide.with( viewHolder.itemView.context ) // La enviamos desde el contexto
            .load( speaker.img ) // Que imagen queremos descargar
            .apply( RequestOptions.circleCropTransform() )  // Cómo queremos que se muestre
            .into( viewHolder.imageViewSpeakerImage )  // Donde queremos que se muestre

        viewHolder.itemView.setOnClickListener {
            speakerListener.onSpeakerClick( speaker, position )
        }

    }

    fun updateData( data: List<Speaker> ) {
        speakers.clear()
        speakers.addAll( data )
        notifyDataSetChanged()
    }

    class ViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ) {
        val textViewSpeakerName: TextView = itemView.findViewById( R.id.textViewItemSpeakerName )
        val textViewSpeakerJobTitle: TextView = itemView.findViewById( R.id.textViewItemSpeakerJobTitle )
        val imageViewSpeakerImage: ImageView = itemView.findViewById( R.id.imageViewItemSpeakerImage )
    }

}