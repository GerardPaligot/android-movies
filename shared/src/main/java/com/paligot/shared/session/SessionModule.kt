package com.paligot.shared.session

import com.paligot.shared.services.TheMovieDatabaseService
import com.paligot.shared.session.database.SessionDatabaseModule
import com.paligot.shared.session.database.SessionSharedPreferenceDataSource
import dagger.Module
import dagger.Provides

@Module(includes = [SessionDatabaseModule::class])
class SessionModule {
  @Provides
  fun provideSessionRepository(
    service: TheMovieDatabaseService,
    database: SessionSharedPreferenceDataSource
  ): SessionRepository =
    SessionRepositoryImpl(service, database)
}