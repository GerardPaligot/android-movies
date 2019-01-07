package com.paligot.shared.session.database

import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Completable
import io.reactivex.Single

class SessionSharedPreferenceDataSourceImpl(preferences: SharedPreferences) : SessionSharedPreferenceDataSource {
  private val rxPreferences: RxSharedPreferences = RxSharedPreferences.create(preferences)
  private val _token: Preference<String> by lazy {
    rxPreferences.getString("TOKEN")
  }
  private val _session: Preference<String> by lazy {
    rxPreferences.getString("SESSION")
  }

  override val token: Single<String>
    get() = _token.asObservable()
      .firstOrError()
      .flatMap {
        if (it.isEmpty()) Single.error(Throwable("Not Found"))
        else Single.just(it)
      }

  override val session: Single<String>
    get() = _session.asObservable()
      .firstOrError()
      .flatMap {
        if (it.isEmpty()) Single.error(Throwable("Not Found"))
        else Single.just(it)
      }


  override fun saveToken(token: String): Single<String> {
    _token.set(token)
    return this.token
  }

  override fun saveSession(session: String): Single<String> {
    _session.set(session)
    return this.session
  }

  override fun clear(): Completable {
    return Completable.create {
      _token.delete()
      _session.delete()
    }
  }
}