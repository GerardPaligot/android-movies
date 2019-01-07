package com.paligot.user

import com.paligot.user.connected.UserConnectedFragment
import com.paligot.user.connected.UserConnectedModule
import com.paligot.user.disconnected.UserDisconnectedFragment
import com.paligot.user.disconnected.UserDisconnectedModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentsProvider {
  @ContributesAndroidInjector(modules = [UserConnectedModule::class])
  fun contributeUserConnectedFragmentInjector(): UserConnectedFragment

  @ContributesAndroidInjector(modules = [UserDisconnectedModule::class])
  fun contributeUserDisconnectedFragmentInjector(): UserDisconnectedFragment
}