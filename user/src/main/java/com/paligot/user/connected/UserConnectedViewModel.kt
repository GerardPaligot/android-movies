package com.paligot.user.connected

import androidx.lifecycle.ViewModel
import com.paligot.shared.livedata.SingleLiveEvent
import com.paligot.shared.session.SessionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class UserConnectedViewModel(
  private val sessionRepository: SessionRepository
) : ViewModel() {
  private val subscription = CompositeDisposable()

  val isNotLogged: SingleLiveEvent<Boolean> by lazy {
    SingleLiveEvent<Boolean>()
  }

  fun updateIsLoggedInfo() {
    subscription.add(
      sessionRepository.isLogged
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer {
          if (it.not())
            isNotLogged.setValue(it)
        })
    )
  }

  fun logout() {
    subscription.add(
      sessionRepository.logout()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
    )
  }

  override fun onCleared() {
    super.onCleared()
    subscription.clear()
  }
}