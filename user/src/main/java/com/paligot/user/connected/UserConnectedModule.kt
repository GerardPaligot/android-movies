package com.paligot.user.connected

import com.paligot.shared.session.SessionRepository
import dagger.Module
import dagger.Provides

@Module
class UserConnectedModule {
  @Provides
  fun provideViewModelFactory(repository: SessionRepository): UserConnectedViewModelFactory =
    UserConnectedViewModelFactory(repository)
}