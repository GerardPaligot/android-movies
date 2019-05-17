package com.paligot.shared.services

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.*

interface TheMovieDatabaseService {
  @GET(value = "/3/authentication/token/new")
  fun requestToken(): Single<Token>

  @POST(value = "/3/authentication/session/new")
  fun requestSession(@Body body: SessionBody): Single<Session>

  @HTTP(method = "DELETE", hasBody = true, path = "/3/authentication/session")
  fun logout(@Body body: LogOutBody): Single<LogOut>

  companion object {
    private const val HOST = "https://www.themoviedb.org"
    const val DOMAIN = "themoviedb.org"
    const val ENDPOINT = "https://api.themoviedb.org"
    const val URL_AUTH = "$HOST/authenticate/%s"
  }
}

data class SessionBody(@SerializedName("request_token") val requestToken: String)

data class LogOutBody(@SerializedName("session_id") val sessionId: String)

data class Token(
  val success: Boolean,
  @SerializedName(value = "expires_at") val expiresAt: String,
  @SerializedName(value = "request_token") val requestToken: String
)

data class Session(
  val success: Boolean,
  @SerializedName(value = "session_id") val sessionId: String
)

data class LogOut(val success: Boolean?)
