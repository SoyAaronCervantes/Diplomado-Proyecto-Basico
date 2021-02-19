package com.soyaaroncervantes.platziconf.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.soyaaroncervantes.platziconf.model.Conference
import com.soyaaroncervantes.platziconf.model.Speaker

const val speakerCollectionName = "speakers"
const val conferenceCollectionName = "conference"

class FirestoreService {
    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings
    }

    fun getSpeakers( callback: Callback< List<Speaker> > ) {
        firebaseFirestore.collection( speakerCollectionName )
            .orderBy("category" )
            .get()
            .addOnSuccessListener { result ->
                for ( document in result ) {
                    val list = result.toObjects( Speaker::class.java )
                    callback.onSuccess( list )
                    break
                }
            }
    }

    fun getSchedule( callback: Callback< List<Conference> > ) {

        firebaseFirestore.collection( conferenceCollectionName )
            .get()
            .addOnSuccessListener { result ->
                for ( doc in result ) {
                    val list = result.toObjects( Conference::class.java )
                    callback.onSuccess( list )
                    break
                }
            }
    }
}
