package com.soyaaroncervantes.platziconf.view.adapter

import com.soyaaroncervantes.platziconf.model.Conference

interface ScheduleListener {
    fun onConferenceClick(conference: Conference, position: Int )
}