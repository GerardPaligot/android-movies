package com.paligot.shared.session.database

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class SessionDatabaseModule {
  @Provides
  fun provideAuthSharedPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences("SHARED_AUTHENTIFICATION", Context.MODE_PRIVATE)

  @Provides
  fun provideSessionDatabase(preferences: SharedPreferences): SessionSharedPreferenceDataSource =
    SessionSharedPreferenceDataSourceImpl(preferences)
}