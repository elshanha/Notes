package com.example.notes

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.notes.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val noteViewModel : NoteViewModel by viewModels()

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(500)
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

    }


}

