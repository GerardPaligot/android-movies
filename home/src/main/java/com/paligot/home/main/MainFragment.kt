package com.paligot.home.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.paligot.home.R
import com.paligot.home.databinding.FragmentMainBinding


class MainFragment : Fragment() {
  private val navigationViewModel: NavigationViewModel by lazy {
    ViewModelProviders.of(requireActivity())[NavigationViewModel::class.java]
  }
  private lateinit var binding: FragmentMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentMainBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
    (childFragmentManager.findFragmentById(R.id.navigationHostFragment) as NavHostFragment).navController.let {
      binding.bottomNavigationView.setupWithNavController(it)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.menu_top_home, menu)
    menu.findItem(R.id.userFragment)?.run {
      actionView.findViewById<View>(R.id.container).setOnClickListener {
        navigationViewModel.goToUserFeature()
      }
    }
  }
}