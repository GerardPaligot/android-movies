package com.paligot.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.paligot.navigation.NavigationViewModel
import com.paligot.navigation.UserNavigation

class MainActivity : AppCompatActivity() {
  private val navigationViewModel: NavigationViewModel by lazy {
    ViewModelProviders.of(this)[NavigationViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigationViewModel.navigation.observe(this, Observer {
      when (it) {
        is UserNavigation -> findNavController(R.id.navigationHostFragment).navigate(R.id.action_mainHomeFragment_to_mainUserFragment)
      }
    })
  }
}