package com.paligot.user.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.paligot.user.R
import com.paligot.user.databinding.ActivityUserMainBinding
import com.paligot.user.userApplication

class MainUserActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(ActivityUserMainBinding.inflate(layoutInflater).run {
      toolbar.title = getString(R.string.user_toolbar)
      toolbar.navigationIcon = ContextCompat.getDrawable(baseContext, com.paligot.style.R.drawable.ic_close)
      return@run root
    })
    userApplication.onCreate(baseContext)
  }
}