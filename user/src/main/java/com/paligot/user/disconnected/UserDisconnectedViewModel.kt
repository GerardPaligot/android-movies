package com.paligot.user.disconnected

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paligot.shared.livedata.SingleLiveEvent
import com.paligot.shared.session.SessionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserDisconnectedViewModel(
  private val sessionRepository: SessionRepository
) : ViewModel() {
  private val subscription = CompositeDisposable()

  private val _token: MutableLiveData<String> by lazy {
    MutableLiveData<String>()
  }
  private val _sessionSaved: MutableLiveData<Boolean> by lazy {
    MutableLiveData<Boolean>()
  }
  private val _error: SingleLiveEvent<Error> by lazy {
    SingleLiveEvent<Error>()
  }

  val token: LiveData<String>
    get() = _token
  val sessionSaved: LiveData<Boolean>
    get() = _sessionSaved
  val error: SingleLiveEvent<Error>
    get() = _error

  fun updateToken() {
    subscription.add(
      sessionRepository.token
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

  fun session() {
    subscription.add(
      sessionRepository.session()
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

  override fun onCleared() {
    super.onCleared()
    subscription.clear()
  }
}

enum class Error {
  TOKEN, SESSION
}