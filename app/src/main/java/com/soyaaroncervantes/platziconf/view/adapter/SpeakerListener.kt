package com.soyaaroncervantes.platziconf.view.adapter

import com.soyaaroncervantes.platziconf.model.Speaker

interface SpeakerListener {
    fun onSpeakerClick( speaker: Speaker, position: Int )
}