package com.paligot.home.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.paligot.shared.livedata.SingleLiveEvent

class NavigationViewModel : ViewModel() {
  private val _navigation: SingleLiveEvent<Navigation> by lazy {
    SingleLiveEvent<Navigation>()
  }
  val navigation: LiveData<Navigation>
    get() = _navigation

  fun goToUserFeature() {
    _navigation.setValue(UserNavigation)
  }
}