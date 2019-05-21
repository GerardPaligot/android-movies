package com.paligot.home

import com.paligot.home.home.HomeFragment
import com.paligot.home.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentsProvider {
  @ContributesAndroidInjector(modules = [HomeModule::class])
  fun contributeHomeFragmentInjector(): HomeFragment
}