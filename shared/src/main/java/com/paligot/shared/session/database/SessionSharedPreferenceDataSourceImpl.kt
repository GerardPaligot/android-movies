package com.paligot.shared.session.database

import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class SessionSharedPreferenceDataSourceImpl(preferences: SharedPreferences) : SessionSharedPreferenceDataSource {
  private val rxPreferences: RxSharedPreferences = RxSharedPreferences.create(preferences)
  private val _session: Preference<String> by lazy {
    rxPreferences.getString("SESSION")
  }
  private val _requestToken: Preference<String> by lazy {
    rxPreferences.getString("REQUEST_TOKEN")
  }
  private val _accessToken: Preference<String> by lazy {
    rxPreferences.getString("ACCESS_TOKEN")
  }
  private val _accountId: Preference<String> by lazy {
    rxPreferences.getString("ACCOUNT_ID")
  }

  override val session: Single<String>
    get() = _session.asObservable().get()
  override val requestToken: Single<String>
    get() = _requestToken.asObservable().get()
  override val accessToken: Single<String>
    get() = _accessToken.asObservable().get()
  override val accountId: Single<String>
    get() = _accountId.asObservable().get()

  private fun Observable<String>.get(): Single<String> {
    return this
      .firstOrError()
      .flatMap {
        if (it.isEmpty()) Single.error(Throwable("Not Found"))
        else Single.just(it)
      }
  }

  override fun saveSession(session: String) {
    _session.set(session)
  }

  override fun saveRequestToken(token: String) {
    _requestToken.set(token)
  }

  override fun saveSessionUser(accessToken: String, accountId: String) {
    _accessToken.set(accessToken)
    _accountId.set(accountId)
  }

  override fun clear(): Completable {
    return Completable.create {
      _session.delete()
      _requestToken.delete()
      _accessToken.delete()
      _accountId.delete()
      it.onComplete()
    }
  }
}