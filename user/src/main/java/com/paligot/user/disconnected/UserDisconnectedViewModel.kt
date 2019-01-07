package com.paligot.user.disconnected

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paligot.shared.session.SessionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

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

  val token: LiveData<String>
    get() = _token
  val sessionSaved: LiveData<Boolean>
    get() = _sessionSaved

  fun updateToken() {
    subscription.add(
      sessionRepository.token
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer {
          _token.value = it
        })
    )
  }

  fun session() {
    subscription.add(
      sessionRepository.session()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer {
          _sessionSaved.value = true
        })
    )
  }

  override fun onCleared() {
    super.onCleared()
    subscription.clear()
  }
}