package com.example.notes.hilt

import android.content.Context
import androidx.room.Room
import com.example.notes.dao.NoteDao
import com.example.notes.database.NoteDatabase
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
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
    fun provideNoteDao(noteDatabase : NoteDatabase) : NoteDao {
        return noteDatabase.getNoteDao()
    }

    @Singleton
    @Provides
    fun provideFirestore() : FirebaseFirestore {
        return Firebase.firestore
    }

    @Singleton
    @Provides
    fun provideFireauth() : FirebaseAuth {
        return Firebase.auth
    }


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context : Context) : NoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note_db").build()
    }

}