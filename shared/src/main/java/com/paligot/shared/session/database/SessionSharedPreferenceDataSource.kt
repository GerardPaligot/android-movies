package com.paligot.shared.session.database

import io.reactivex.Completable
import io.reactivex.Single

interface SessionSharedPreferenceDataSource {
  val token: Single<String>
  val session: Single<String>
  fun saveToken(token: String): Single<String>
  fun saveSession(session: String): Single<String>
  fun clear(): Completable
}