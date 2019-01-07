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
import com.paligot.navigation.NavigationViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {
  private val navigationViewModel: NavigationViewModel by lazy {
    ViewModelProviders.of(requireActivity())[NavigationViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return FragmentMainBinding.inflate(inflater, container, false).root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
    (childFragmentManager.findFragmentById(R.id.navigationHostFragment) as NavHostFragment).navController.let {
      bottomNavigationView.setupWithNavController(it)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater?.inflate(R.menu.menu_top_home, menu)
    menu?.findItem(R.id.userFragment)?.run {
      actionView.findViewById<View>(R.id.container).setOnClickListener {
        navigationViewModel.goToUserFeature()
      }
    }
  }
}