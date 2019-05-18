package com.paligot.shared.session

import com.paligot.shared.session.database.SessionDatabaseModule
import com.paligot.shared.session.database.SessionSharedPreferenceDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [SessionDatabaseModule::class])
class SessionModule {
  @Provides
  fun provideSessionRepository(
    service: SessionService,
    database: SessionSharedPreferenceDataSource
  ): SessionRepository = SessionRepositoryImpl(service, database)

  @Provides
  fun provideTheMovieDatabaseService(retrofit: Retrofit): SessionService = retrofit.create(SessionService::class.java)
}