package com.example.notes.hilt

import com.example.notes.utility.MySharedPreferences
import com.example.notes.utility.MySharedPreferencesInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SharedPreferncesModule {

    @Binds
    @Singleton
    abstract fun bindPreferences(pref : MySharedPreferences) : MySharedPreferencesInterface
}