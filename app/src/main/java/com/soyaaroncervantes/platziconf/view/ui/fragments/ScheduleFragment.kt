package com.soyaaroncervantes.platziconf.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soyaaroncervantes.platziconf.R
import com.soyaaroncervantes.platziconf.model.Conference
import com.soyaaroncervantes.platziconf.view.adapter.ScheduleAdapter
import com.soyaaroncervantes.platziconf.view.adapter.ScheduleListener
import com.soyaaroncervantes.platziconf.view.adapter.SpeakerAdapter
import com.soyaaroncervantes.platziconf.viewmodel.ScheduleViewModel
import com.soyaaroncervantes.platziconf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : Fragment(), ScheduleListener {
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var scheduleViewModel: ScheduleViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleViewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        scheduleViewModel.refresh()

        scheduleAdapter = ScheduleAdapter( this )

        recyclerViewSchedule.apply {
            layoutManager = LinearLayoutManager( view.context, LinearLayoutManager.VERTICAL, false )
            adapter = scheduleAdapter
        }
        observerViewModel()
    }

    private fun observerViewModel() {
        scheduleViewModel.listSchedule.observe( viewLifecycleOwner, {
            it.let { scheduleAdapter.updateData( it ) }
        })

        scheduleViewModel.isLoading.observe( this.viewLifecycleOwner, {
            if ( it != null ) {
                relativeLayoutBaseSchedule.visibility = View.INVISIBLE
            }
        })
    }

    override fun onConferenceClick(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference )
        findNavController().navigate( R.id.scheduleDetailFragmentDialog, bundle )
    }
}