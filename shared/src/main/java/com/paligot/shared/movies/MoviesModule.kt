package com.paligot.shared.movies

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MoviesModule {
  @Provides
  fun provideMoviesService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)
}