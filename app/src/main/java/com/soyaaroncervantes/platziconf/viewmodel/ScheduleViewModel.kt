package com.soyaaroncervantes.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soyaaroncervantes.platziconf.model.Conference
import com.soyaaroncervantes.platziconf.network.Callback
import com.soyaaroncervantes.platziconf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel() {
    private val firestoreService = FirestoreService()
    val listSchedule: MutableLiveData< List<Conference> > = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }

    private fun getScheduleFromFirebase(){
        firestoreService.getSchedule( object: Callback<List<Conference>> {
            override fun onSuccess(result: List<Conference>) {
                listSchedule.postValue( result )
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    private fun processFinished() {
        isLoading.value = true
    }
}