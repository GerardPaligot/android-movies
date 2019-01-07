package com.paligot.user

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class UserApplication internal constructor() : HasSupportFragmentInjector {
  @Inject
  lateinit var injector: DispatchingAndroidInjector<Fragment>
  lateinit var component: UserComponent

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = injector

  fun onCreate(context: Context) {
    component = DaggerUserComponent.builder()
      .context(context)
      .build()
    component.inject(component = this)
  }
}

private val appSingleton = UserApplication()

val Fragment.userApplication: UserApplication
  get() = appSingleton