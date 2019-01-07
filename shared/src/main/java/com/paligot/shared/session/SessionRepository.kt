package com.paligot.shared.session

import io.reactivex.Completable
import io.reactivex.Single

interface SessionRepository {
  val isLogged: Single<Boolean>
  val token: Single<String>
  fun session(): Single<String>
  fun logout(): Completable
}