package com.paligot.user.connected

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paligot.shared.session.SessionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserConnectedViewModel(private val sessionRepository: SessionRepository) : ViewModel() {
  private val subscription = CompositeDisposable()

  private val _user: MutableLiveData<UserUi> by lazy {
    MutableLiveData<UserUi>()
  }

  val user: LiveData<UserUi>
    get() = _user

  fun requestAccount() {
    subscription.add(
      sessionRepository.account()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          _user.value = UserUi(it.username, it.avatarUrl)
        }, {
          Timber.e(it)
        })
    )
  }

  override fun onCleared() {
    super.onCleared()
    subscription.clear()
  }
}

class UserConnectedViewModelFactory(private val sessionRepository: SessionRepository) : ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass != UserConnectedViewModel::class.java) {
      throw IllegalArgumentException("Unknown ViewModel class")
    }
    return UserConnectedViewModel(sessionRepository) as T
  }
}