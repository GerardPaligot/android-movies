package com.paligot.user.connected

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paligot.shared.session.SessionRepository

class UserConnectedViewModelFactory(
  private val sessionRepository: SessionRepository
) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass != UserConnectedViewModel::class.java) {
      throw IllegalArgumentException("Unknown ViewModel class")
    }
    return UserConnectedViewModel(sessionRepository) as T
  }
}