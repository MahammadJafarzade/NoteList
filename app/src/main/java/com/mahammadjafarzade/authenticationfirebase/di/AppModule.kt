package com.sirketismi.noteapp.di

import android.content.Context
import androidx.room.Room
import com.mahammadjafarzade.authenticationfirebase.dao.Appdatabase
import com.mahammadjafarzade.authenticationfirebase.dao.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDao(appDatabase : Appdatabase) : NotesDao {
        return appDatabase.noteDao()
    }

    @Singleton
    @Provides
    fun provideAppdatabase(@ApplicationContext appContext: Context) : Appdatabase {
        val instance = Room.databaseBuilder(
            appContext.applicationContext,
            Appdatabase::class.java,
            "noteList"
        ).build()
        return instance
    }
}