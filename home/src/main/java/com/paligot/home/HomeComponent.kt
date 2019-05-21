package com.paligot.home

import android.content.Context
import com.paligot.shared.ShareModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
  modules = [AndroidSupportInjectionModule::class, ShareModule::class, FragmentsProvider::class]
)
interface HomeComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun context(context: Context): Builder

    fun build(): HomeComponent
  }

  fun inject(component: HomeApplication)
}