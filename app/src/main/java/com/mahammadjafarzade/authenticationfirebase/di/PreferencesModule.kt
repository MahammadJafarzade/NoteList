package com.mahammadjafarzade.authenticationfirebase.di

import com.mahammadjafarzade.authenticationfirebase.util.MySharedPreferences
import com.mahammadjafarzade.authenticationfirebase.util.MySharedPreferencesInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

abstract class PreferencesModule {
    @Binds
    @Singleton
    abstract fun bindPreferences(pref : MySharedPreferences) : MySharedPreferencesInterface
}