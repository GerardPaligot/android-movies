package com.paligot.home.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.Config
import androidx.paging.toLiveData
import com.paligot.shared.livedata.SingleLiveEvent
import com.paligot.shared.movies.*
import com.paligot.shared.session.SessionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class HomeViewModel(service: MoviesService, private val session: SessionRepository) : ViewModel() {
  private val subscription = CompositeDisposable()

  private val _isLogged: SingleLiveEvent<Boolean> by lazy {
    SingleLiveEvent<Boolean>()
  }
  val isLogged: LiveData<Boolean>
    get() = _isLogged

  val popular = MovieDataSource.Factory(POPULAR, service, session).toLiveData(Config(20, 20))
  val trending = MovieDataSource.Factory(TRENDING, service, session).toLiveData(Config(20, 20))
  val upComing = MovieDataSource.Factory(UP_COMING, service, session).toLiveData(Config(20, 20))
  val topSelling = MovieDataSource.Factory(TOP_SELLING, service, session).toLiveData(Config(20, 20))
  private val factoryRecommendations = MovieDataSource.Factory(RECOMMENDATION, service, session)
  val recommendations = factoryRecommendations.toLiveData(Config(20, 20))

  fun invalidate() {
    factoryRecommendations.source.value?.invalidate()
  }

  fun updateIsLoggedInfo() {
    subscription.add(
      session.isLogged
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          if (_isLogged.value != it) _isLogged.setValue(it)
        }, {
          Timber.e(it)
        })
    )
  }

  override fun onCleared() {
    super.onCleared()
    subscription.clear()
  }

  class Factory(private val service: MoviesService, private val session: SessionRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      if (modelClass != HomeViewModel::class.java) {
        throw IllegalArgumentException("Unknown ViewModel class")
      }
      return HomeViewModel(service, session) as T
    }
  }
}