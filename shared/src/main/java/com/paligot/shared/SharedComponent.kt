package com.paligot.shared

import com.paligot.shared.movies.MoviesModule
import com.paligot.shared.services.ServiceModule
import com.paligot.shared.session.SessionModule
import dagger.Component
import dagger.Module

@Component(
  modules = [ServiceModule::class, SessionModule::class, MoviesModule::class]
)
interface SharedComponent {
  @Component.Builder
  interface Builder {
    fun build(): SharedComponent
  }
}

@Module(includes = [ServiceModule::class, SessionModule::class, MoviesModule::class])
class ShareModule