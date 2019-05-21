package com.paligot.user.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paligot.shared.livedata.SingleLiveEvent
import com.paligot.shared.session.SessionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserViewModel(private val sessionRepository: SessionRepository) : ViewModel() {
  private val subscription = CompositeDisposable()

  private val _token: MutableLiveData<String> by lazy {
    MutableLiveData<String>()
  }
  private val _sessionSaved: MutableLiveData<Boolean> by lazy {
    MutableLiveData<Boolean>()
  }
  private val _sessionDeleted: MutableLiveData<Boolean> by lazy {
    MutableLiveData<Boolean>()
  }
  private val _error: SingleLiveEvent<Error> by lazy {
    SingleLiveEvent<Error>()
  }
  private val _isLogged: SingleLiveEvent<Boolean> by lazy {
    SingleLiveEvent<Boolean>()
  }

  val token: LiveData<String>
    get() = _token
  val sessionSaved: LiveData<Boolean>
    get() = _sessionSaved
  val sessionDeleted: LiveData<Boolean>
    get() = _sessionDeleted
  val isLogged: LiveData<Boolean>
    get() = _isLogged
  val error: SingleLiveEvent<Error>
    get() = _error

  fun updateIsLoggedInfo() {
    subscription.add(
      sessionRepository.isLogged
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          _isLogged.setValue(it)
        }, {
          Timber.e(it)
        })
    )
  }

  fun requestToken() {
    subscription.add(
      sessionRepository.requestToken()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          _token.value = it
        }, {
          Timber.e(it)
          _error.setValue(Error.TOKEN)
        })
    )
  }

  fun requestSession() {
    subscription.add(
      sessionRepository.accessToken()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          _sessionSaved.value = true
        }, {
          Timber.e(it)
          _error.setValue(Error.SESSION)
        })
    )
  }

  fun logout() {
    subscription.add(
      sessionRepository.logout()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          _sessionDeleted.value = true
        }, {
          Timber.e(it)
          _sessionDeleted.value = false
        })
    )
  }

  override fun onCleared() {
    super.onCleared()
    subscription.clear()
  }
}

enum class Error {
  TOKEN, SESSION
}

class UserViewModelFactory(private val sessionRepository: SessionRepository) : ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass != UserViewModel::class.java) {
      throw IllegalArgumentException("Unknown ViewModel class")
    }
    return UserViewModel(sessionRepository) as T
  }
}