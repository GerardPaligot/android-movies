package com.paligot.shared.services

import com.google.gson.Gson
import com.paligot.shared.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ServiceModule {

  @Provides
  fun provideOkHttpClientBuilder(): OkHttpClient.Builder =
    OkHttpClient.Builder()
      .addInterceptor(ClientAuthInterceptor())
      .addInterceptor(HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
          HttpLoggingInterceptor.Level.BODY
        } else {
          HttpLoggingInterceptor.Level.NONE
        }
      })

  @Provides
  fun provideRetrofit(okHttpBuilder: OkHttpClient.Builder): Retrofit = Retrofit.Builder()
    .baseUrl(TheMovieDatabaseService.ENDPOINT)
    .client(okHttpBuilder.build())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(Gson()))
    .build()

  @Provides
  fun provideTheMovieDatabaseService(retrofit: Retrofit): TheMovieDatabaseService =
    retrofit.create(TheMovieDatabaseService::class.java)
}

class ClientAuthInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val url = chain.request().url().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
    return chain.proceed(
      chain.request().newBuilder()
        .url(url)
        .build()
    )
  }
}