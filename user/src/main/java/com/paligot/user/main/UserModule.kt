package com.paligot.user.main

import com.paligot.shared.session.SessionRepository
import dagger.Module
import dagger.Provides

@Module
class UserModule {
  @Provides
  fun provideViewModelFactory(repository: SessionRepository): UserViewModelFactory =
    UserViewModelFactory(repository)
}