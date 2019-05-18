package com.paligot.shared.session

import com.paligot.shared.services.URL_AUTH_V4
import com.paligot.shared.session.database.SessionSharedPreferenceDataSource
import io.reactivex.Completable
import io.reactivex.Single

class SessionRepositoryImpl(
  private val service: SessionService,
  private val database: SessionSharedPreferenceDataSource
) : SessionRepository {
  override val isLogged: Single<Boolean>
    get() = database.accessToken
      .map { true }
      .onErrorReturn { false }

  override val requestToken: Single<String>
    get() = service.requestToken()
      .doOnSuccess { database.saveRequestToken(it.requestToken) }
      .map { URL_AUTH_V4.format(it.requestToken) }

  override val accessToken: Single<String>
    get() = database.requestToken
      .flatMap { service.requestAccessToken(AccessTokenBody(it)) }
      .flatMap { token ->
        return@flatMap service.convertSession(ConvertSessionBody(token.accessToken))
          .doOnSuccess {
            database.saveSessionUser(token.accessToken, token.accountId)
            database.saveSession(it.sessionId)
          }
          .map { token.accessToken }
      }

  override fun logout(): Completable = database.accessToken
    .flatMap { service.logout(LogOutBody(it)) }
    .onErrorReturn { LogOut(success = false) }
    .flatMapCompletable { database.clear() }

  override val accountId: Single<String>
    get() = database.accountId

  override fun account(): Single<User> = database.session.flatMap { service.details(it) }
}