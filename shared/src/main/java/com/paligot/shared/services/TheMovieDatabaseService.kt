package com.paligot.shared.services

import com.google.gson.annotations.SerializedName
import com.paligot.shared.BuildConfig
import io.reactivex.Single
import retrofit2.http.*

interface TheMovieDatabaseService {
  @POST(value = "/3/authentication/session/convert/4")
  @Headers("Content-Type: application/json;charset=utf-8")
  fun convertSession(@Body body: ConvertSessionBody): Single<Session>

  // Return always a 404. Ticket: https://trello.com/c/Q1ceDQ8e/18-deleting-a-v4-access-token-does-not-work
  @HTTP(method = "DELETE", hasBody = true, path = "/4/auth/access_token")
  @Headers("Content-Type: application/json;charset=utf-8", "Authorization: Bearer ${BuildConfig.TOKEN_V4}")
  fun logout(@Body body: LogOutBody): Single<LogOut>

  @POST(value = "/4/auth/request_token")
  @Headers("Content-Type: application/json;charset=utf-8", "Authorization: Bearer ${BuildConfig.TOKEN_V4}")
  fun requestToken(): Single<RequestToken>

  @POST(value = "/4/auth/access_token")
  @Headers("Content-Type: application/json;charset=utf-8", "Authorization: Bearer ${BuildConfig.TOKEN_V4}")
  fun requestAccessToken(@Body body: AccessTokenBody): Single<AccessToken>

  @GET(value = "/3/account")
  fun details(@Query("session_id") sessionId: String): Single<User>

  companion object {
    private const val HOST = "https://www.themoviedb.org"
    const val DOMAIN = "themoviedb.org"
    const val ENDPOINT = "https://api.themoviedb.org"
    const val URL_AUTH_V4 = "$HOST/auth/access?request_token=%s"
  }
}

data class ConvertSessionBody(@SerializedName("access_token") val accessToken: String)
data class AccessTokenBody(@SerializedName("request_token") val requestToken: String)
data class LogOutBody(@SerializedName("access_token") val accessToken: String)

data class Session(
  val success: Boolean,
  @SerializedName(value = "session_id") val sessionId: String
)

data class LogOut(val success: Boolean?)

data class RequestToken(
  val success: Boolean,
  @SerializedName(value = "request_token") val requestToken: String
)

data class AccessToken(
  val success: Boolean,
  @SerializedName(value = "access_token") val accessToken: String,
  @SerializedName(value = "account_id") val accountId: String
)

data class User(val id: Long, val username: String, val avatar: Avatar) {
  val avatarUrl: String
    get() = "https://s.gravatar.com/avatar/${this.avatar.gravatar.hash}?s=100"
}

data class Avatar(val gravatar: Gravatar)
data class Gravatar(val hash: String)
