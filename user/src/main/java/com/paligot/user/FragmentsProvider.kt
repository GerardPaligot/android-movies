package com.paligot.user

import com.paligot.user.connected.UserConnectedFragment
import com.paligot.user.disconnected.UserDisconnectedFragment
import com.paligot.user.loading.UserLoadingFragment
import com.paligot.user.main.UserModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentsProvider {
  @ContributesAndroidInjector(modules = [UserModule::class])
  fun contributeUserLoadingFragmentInjector(): UserLoadingFragment

  @ContributesAndroidInjector(modules = [UserModule::class])
  fun contributeUserConnectedFragmentInjector(): UserConnectedFragment

  @ContributesAndroidInjector(modules = [UserModule::class])
  fun contributeUserDisconnectedFragmentInjector(): UserDisconnectedFragment
}