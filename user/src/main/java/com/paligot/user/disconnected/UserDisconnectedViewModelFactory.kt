package com.paligot.user.disconnected

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paligot.shared.session.SessionRepository

class UserDisconnectedViewModelFactory(
  private val sessionRepository: SessionRepository
) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass != UserDisconnectedViewModel::class.java) {
      throw IllegalArgumentException("Unknown ViewModel class")
    }
    return UserDisconnectedViewModel(sessionRepository) as T
  }
}