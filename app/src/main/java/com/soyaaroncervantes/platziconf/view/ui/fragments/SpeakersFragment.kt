package com.soyaaroncervantes.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.soyaaroncervantes.platziconf.R
import com.soyaaroncervantes.platziconf.model.Speaker
import com.soyaaroncervantes.platziconf.view.adapter.SpeakerAdapter
import com.soyaaroncervantes.platziconf.view.adapter.SpeakerListener
import com.soyaaroncervantes.platziconf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

class SpeakersFragment : Fragment(), SpeakerListener {
    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var speakersViewModel: SpeakersViewModel;

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        speakersViewModel = ViewModelProvider( this ).get( SpeakersViewModel::class.java )
        speakersViewModel.refresh()

        speakerAdapter = SpeakerAdapter( this )

        recyclerViewSpeaker.apply {
            layoutManager = GridLayoutManager( view.context, 2 )
            adapter = speakerAdapter
        }

        observerViewModel()
    }

    private fun observerViewModel() {
        speakersViewModel.listSchedule.observe( viewLifecycleOwner, {
            speaker -> speakerAdapter.updateData( speaker )
        })

        speakersViewModel.isLoading.observe( viewLifecycleOwner, {
            if ( it != null ) { relativeLayoutBaseSpeaker.visibility = View.INVISIBLE }
        })
    }

    override fun onSpeakerClick(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker )
        findNavController().navigate( R.id.speakersDetailFragmentDialog, bundle )
    }

}