package com.paligot.user.disconnected

import androidx.lifecycle.ViewModelProviders
import com.paligot.shared.session.SessionRepository
import dagger.Module
import dagger.Provides

@Module
class UserDisconnectedModule {
  @Provides
  fun provideViewModelFactory(repository: SessionRepository): UserDisconnectedViewModelFactory =
    UserDisconnectedViewModelFactory(repository)

  @Provides
  fun provideViewModel(
    fragment: UserDisconnectedFragment,
    factory: UserDisconnectedViewModelFactory
  ): UserDisconnectedViewModel =
    ViewModelProviders.of(fragment, factory)[UserDisconnectedViewModel::class.java]
}