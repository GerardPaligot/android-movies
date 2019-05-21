package com.paligot.home.home

import com.paligot.shared.movies.MoviesService
import com.paligot.shared.session.SessionRepository
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
  @Provides
  fun provideViewModelFactory(service: MoviesService, session: SessionRepository): HomeViewModel.Factory =
    HomeViewModel.Factory(service, session)
}