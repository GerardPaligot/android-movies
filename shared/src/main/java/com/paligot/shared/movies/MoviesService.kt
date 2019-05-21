package com.paligot.shared.movies

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
  @GET(value = "/3/discover/movie?include_adult=false&sort_by=popularity.desc")
  fun populars(@Query("language") language: String, @Query("region") region: String, @Query("page") page: Int): Single<PagingList<Movie>>

  @GET(value = "/3/trending/movie/week")
  fun trending(@Query("language") language: String, @Query("region") region: String, @Query("page") page: Int): Single<PagingList<Movie>>

  @GET(value = "/3/movie/upcoming")
  fun upComing(@Query("language") language: String, @Query("region") region: String, @Query("page") page: Int): Single<PagingList<Movie>>

  @GET(value = "/3/discover/movie?include_adult=false&sort_by=revenue.desc")
  fun topSelling(
    @Query("language") language: String,
    @Query("region") region: String,
    @Query("primary_release_date.gte") fromDate: String,
    @Query("primary_release_date.lte") toDate: String,
    @Query("page") page: Int
  ): Single<PagingList<Movie>>

  @GET(value = "/4/account/{account_id}/movie/recommendations")
  fun recommendations(
    @Header("Authorization") token: String,
    @Path("account_id") accountId: String,
    @Query("page") page: Int
  ): Single<PagingList<Movie>>
}

data class PagingList<T>(
  val page: Int,
  @SerializedName("total_results") val count: Int,
  @SerializedName("total_pages") val total: Int,
  val results: List<T>
)

data class Movie(
  val id: Long,
  val title: String,
  @SerializedName("poster_path") val poster: String
) {
  val posterUrl: String
    get() = "https://image.tmdb.org/t/p/w200${poster}"
}