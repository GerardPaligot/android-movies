package com.paligot.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
  private val _navigation: MutableLiveData<Navigation> by lazy {
    MutableLiveData<Navigation>()
  }
  val navigation: LiveData<Navigation>
    get() = _navigation

  fun goToUserFeature() {
    _navigation.value = UserNavigation
  }

  fun popBackStack() {
    _navigation.value = BackNavigation
  }
}