package com.soyaaroncervantes.platziconf.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.soyaaroncervantes.platziconf.R
import com.soyaaroncervantes.platziconf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat
import java.util.*

class ScheduleDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle( STYLE_NORMAL, R.style.FullScreenDialogStyle  )
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarConference.navigationIcon = ContextCompat.getDrawable( view.context, R.drawable.ic_close )
        toolbarConference.setTitleTextColor( Color.WHITE )
        toolbarConference.setNavigationOnClickListener { dismiss() }

        val conference = arguments?.getSerializable("conference") as Conference
        toolbarConference.title = conference.title

        textViewScheduleDetailTitle.text = conference.title
        val pattern = "dd/MM/yyyy hh::mm a"
        val dateFormat = SimpleDateFormat( pattern, Locale.ROOT )
        val date = dateFormat.format( conference.datetime )
        textViewScheduleDetailHour.text = date
        textViewScheduleDetailSpeaker.text = conference.speaker
        textViewScheduleDetailTag.text = conference.tag
        textViewScheduleDetailDescription.text = conference.description
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT )
    }
}