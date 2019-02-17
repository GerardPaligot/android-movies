package com.paligot.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.paligot.home.main.NavigationViewModel
import com.paligot.home.main.UserNavigation

class MainActivity : AppCompatActivity() {
  private val navigationViewModel: NavigationViewModel by lazy {
    ViewModelProviders.of(this)[NavigationViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigationViewModel.navigation.observe(this, Observer {
      when (it) {
        is UserNavigation -> {
          startActivity(intentTo(this, Activities.User))
          overridePendingTransition(R.anim.slide_in_from_down, android.R.anim.fade_out);
        }
      }
    })
  }
}