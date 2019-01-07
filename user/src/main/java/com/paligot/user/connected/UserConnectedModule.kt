package com.paligot.user.connected

import androidx.lifecycle.ViewModelProviders
import com.paligot.shared.session.SessionRepository
import dagger.Module
import dagger.Provides

@Module
class UserConnectedModule {
  @Provides
  fun provideViewModelFactory(repository: SessionRepository): UserConnectedViewModelFactory =
    UserConnectedViewModelFactory(repository)

  @Provides
  fun provideViewModel(
    fragment: UserConnectedFragment,
    factory: UserConnectedViewModelFactory
  ): UserConnectedViewModel =
    ViewModelProviders.of(fragment, factory)[UserConnectedViewModel::class.java]
}