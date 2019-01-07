package com.paligot.user

import android.content.Context
import com.paligot.shared.ShareModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
  modules = [AndroidSupportInjectionModule::class, ShareModule::class, FragmentsProvider::class]
)
interface UserComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun context(context: Context): Builder
    fun build(): UserComponent
  }

  fun inject(component: UserApplication)
}