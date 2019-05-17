package com.paligot.shared.session

import com.paligot.shared.services.LogOut
import com.paligot.shared.services.LogOutBody
import com.paligot.shared.services.SessionBody
import com.paligot.shared.services.TheMovieDatabaseService
import com.paligot.shared.session.database.SessionSharedPreferenceDataSource
import io.reactivex.Completable
import io.reactivex.Single

class SessionRepositoryImpl(
  private val service: TheMovieDatabaseService,
  private val database: SessionSharedPreferenceDataSource
) : SessionRepository {
  override val isLogged: Single<Boolean>
    get() = database.session
      .map { true }
      .onErrorReturn { false }

  override val token: Single<String>
    get() = service.requestToken()
      .flatMap { database.saveToken(it.requestToken) }
      .map { TheMovieDatabaseService.URL_AUTH.format(it) }

  override fun session(): Single<String> = database.token
    .flatMap { service.requestSession(SessionBody(it)) }
    .flatMap { database.saveSession(it.sessionId) }

  override fun logout(): Completable = database.session
    .flatMap { service.logout(LogOutBody(it)).onErrorReturn { LogOut(success = false) } }
    .flatMapCompletable { database.clear() }
}