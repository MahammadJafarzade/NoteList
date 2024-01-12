package com.mahammadjafarzade.authenticationfirebase.di

import com.mahammadjafarzade.authenticationfirebase.repository.NoteRepositoryInterface
import com.mahammadjafarzade.authenticationfirebase.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNoteRepository(im : NotesRepository) : NoteRepositoryInterface
}