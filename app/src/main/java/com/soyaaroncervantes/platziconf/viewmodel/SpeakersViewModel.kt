package com.soyaaroncervantes.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soyaaroncervantes.platziconf.model.Speaker
import com.soyaaroncervantes.platziconf.network.Callback
import com.soyaaroncervantes.platziconf.network.FirestoreService
import java.lang.Exception

class SpeakersViewModel: ViewModel() {
    private val firestoreService = FirestoreService()
    val listSchedule: MutableLiveData< List<Speaker> > = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakersFromFirebase()
    }

    private fun getSpeakersFromFirebase(){
        firestoreService.getSpeakers( object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>) {
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