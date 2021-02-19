package com.soyaaroncervantes.platziconf.view.adapter

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soyaaroncervantes.platziconf.R
import com.soyaaroncervantes.platziconf.model.Conference
import java.util.*

class ScheduleAdapter( private val scheduleListener: ScheduleListener ) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    private val conferences = arrayListOf<Conference>()

    // El método que nos dice cuaál va a ser el diseño para nuestra lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        // Esta linea es para decirle cua es el archivo que necesitamos utilizar para el diseño de un item en nuestro recycleView
        ViewHolder( LayoutInflater.from( parent.context ).inflate( R.layout.item_schedule, parent, false ) )

    // Cuantos elementos tenemos
    override fun getItemCount() = conferences.size

    // Los datos que vamos a cargar en el archivo XML
    override fun onBindViewHolder( viewHolder: ViewHolder, position: Int) {
        val conference = conferences[ position ]
        viewHolder.textViewConferenceSpeaker.text = conference.speaker
        viewHolder.textViewConferenceName.text = conference.title
        viewHolder.textViewConferenceTag.text = conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault() )
        val simpleDateFormatPeriod = SimpleDateFormat( "a", Locale.getDefault() )

        val calendar = Calendar.getInstance()
        calendar.time = conference.datetime

        val timeFormat = simpleDateFormat.format( conference.datetime )

        viewHolder.textViewConferenceHour.text = timeFormat
        viewHolder.textViewConferencePeriod.text = simpleDateFormatPeriod
            .format( conference.datetime )
            .toUpperCase(Locale.ROOT)

        viewHolder.itemView.setOnClickListener {
            scheduleListener.onConferenceClick( conference, position )
        }

    }

    fun updateData( data: List<Conference> ) {
        conferences.clear();
        conferences.addAll( data )
        notifyDataSetChanged()
    }

    // Referencias visuales del archivo XML
    class ViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ) {
        val textViewConferenceName =        itemView.findViewById<TextView>( R.id.textViewItemScheduleConferenceName )
        val textViewConferenceSpeaker=      itemView.findViewById<TextView>( R.id.textViewItemScheduleConferenceSpeaker )
        val textViewConferenceTag=          itemView.findViewById<TextView>( R.id.textViewItemScheduleTag )
        val textViewConferenceHour=         itemView.findViewById<TextView>( R.id.textViewItemScheduleHour )
        val textViewConferencePeriod =      itemView.findViewById<TextView>( R.id.textViewItemSchedulePeriod )
    }


}