package com.paligot.shared.session.database

import io.reactivex.Completable
import io.reactivex.Single

interface SessionSharedPreferenceDataSource {
  val session: Single<String>
  val requestToken: Single<String>
  val accessToken: Single<String>
  val accountId: Single<String>
  fun saveSession(session: String)
  fun saveRequestToken(token: String)
  fun saveSessionUser(accessToken: String, accountId: String)
  fun clear(): Completable
}