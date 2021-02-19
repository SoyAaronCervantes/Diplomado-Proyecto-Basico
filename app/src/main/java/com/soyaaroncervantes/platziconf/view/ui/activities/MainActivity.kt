package com.soyaaroncervantes.platziconf.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.soyaaroncervantes.platziconf.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setActionBar( findViewById( R.id.toolbarMain ) )
    configNavigation()
  }

  private fun configNavigation() {
    val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContent) as NavHostFragment
    val navController = navHostFragment.navController

    NavigationUI.setupWithNavController( bottomNavigationMenu, navController )
  }

}