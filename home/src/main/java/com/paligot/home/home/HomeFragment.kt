package com.paligot.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.paligot.home.databinding.FragmentHomeBinding
import com.paligot.home.homeApplication
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : Fragment() {
  @Inject
  lateinit var viewModelFactory: HomeViewModel.Factory
  private val viewModel: HomeViewModel by viewModels { viewModelFactory }
  private lateinit var binding: FragmentHomeBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Timber.plant(Timber.DebugTree())
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return FragmentHomeBinding.inflate(inflater, container, false).run {
      binding = this
      mediaRecyclerView.apply {
        setHasFixedSize(true)
        adapter = HomeAdapter()
      }
      return@run root
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    homeApplication.injector.maybeInject(this)
    viewModel.popular.observe(this, Observer {
      (binding.mediaRecyclerView.adapter as HomeAdapter).addPopular(MediaListUi("Popular", it))
      binding.mediaRecyclerView.smoothScrollToPosition(0)
    })
    viewModel.upComing.observe(this, Observer {
      (binding.mediaRecyclerView.adapter as HomeAdapter).addUpComing(MediaListUi("Up Coming", it))
      binding.mediaRecyclerView.smoothScrollToPosition(0)
    })
    viewModel.trending.observe(this, Observer {
      (binding.mediaRecyclerView.adapter as HomeAdapter).addTrending(MediaListUi("Trending", it))
      binding.mediaRecyclerView.smoothScrollToPosition(0)
    })
    viewModel.topSelling.observe(this, Observer {
      (binding.mediaRecyclerView.adapter as HomeAdapter).addTopSelling(MediaListUi("Top Selling", it))
      binding.mediaRecyclerView.smoothScrollToPosition(0)
    })
    viewModel.recommendations.observe(this, Observer {
      (binding.mediaRecyclerView.adapter as HomeAdapter).addRecommendations(MediaListUi("Recommendations", it))
      binding.mediaRecyclerView.smoothScrollToPosition(0)
    })
    viewModel.isLogged.observe(this, Observer {
      if (it) viewModel.invalidate()
      else (binding.mediaRecyclerView.adapter as HomeAdapter).removeRecommendations()
    })
  }

  override fun onStart() {
    super.onStart()
    viewModel.updateIsLoggedInfo()
  }
}