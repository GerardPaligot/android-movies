package com.paligot.shared.session

import io.reactivex.Completable
import io.reactivex.Single

interface SessionRepository {
  val isLogged: Single<Boolean>
  val requestToken: Single<String>
  val accessToken: Single<String>
  fun logout(): Completable
  val accountId: Single<String>
  fun account(): Single<User>
}