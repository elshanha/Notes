package com.example.notes

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.notes.model.Note
import com.example.notes.viewmodels.NoteViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val noteViewModel : NoteViewModel by viewModels()

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController


        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                when(navController.currentDestination?.id) {
                    R.id.editNoteFragment ->  navController.navigate(R.id.homeFragment)
                    R.id.addNoteFragment ->  navController.navigate(R.id.homeFragment)
                    R.id.favoritesFragment -> navController.navigate(R.id.homeFragment)
                }
            }
        })

//        val cloudDb = Firebase.firestore
//
//        var note = hashMapOf(
//            "title" to "Note App",
//            "id" to "1"
//        )
//
//        val noteCollection = cloudDb.collection("Notes")
//
//        noteCollection.add(note)

    }





}

