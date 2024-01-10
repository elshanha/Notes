package com.example.notes.hilt

import com.example.notes.repository.FirebaseRepository
import com.example.notes.repository.FirebaseRepositoryInterface
import com.example.notes.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

//    @Binds
//    @Singleton
//    abstract fun bindNoteRepository(im : NoteRepository) : NoteRepositoryInterface

    @Binds
    @Singleton
    abstract fun bindFirebaseRepository(im : FirebaseRepository) : FirebaseRepositoryInterface

}