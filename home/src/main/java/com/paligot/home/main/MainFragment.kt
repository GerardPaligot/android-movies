package com.paligot.home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.paligot.home.R
import com.paligot.home.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return FragmentMainBinding.inflate(inflater, container, false).root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (childFragmentManager.findFragmentById(R.id.navigationHostFragment) as NavHostFragment).navController.let {
      bottomNavigationView.setupWithNavController(it)
    }
  }
}