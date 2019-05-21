package com.paligot.home

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class HomeApplication internal constructor() : HasSupportFragmentInjector {
  @Inject
  lateinit var injector: DispatchingAndroidInjector<Fragment>
  lateinit var component: HomeComponent

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = injector

  fun onCreate(context: Context) {
    component = DaggerHomeComponent.builder()
      .context(context)
      .build()
    component.inject(component = this)
  }
}

private val appSingleton = HomeApplication()

val Activity.homeApplication: HomeApplication
  get() = appSingleton
val Fragment.homeApplication: HomeApplication
  get() = appSingleton