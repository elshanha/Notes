package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notes.dao.NoteDao
import com.example.notes.model.Note


@Database(entities = [Note::class], version = 3, exportSchema = true)
abstract class NoteDatabase : RoomDatabase() {


    abstract fun getNoteDao() : NoteDao


    companion object{

        @Volatile
        private var instance :NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance ?:
        synchronized(LOCK) {
            instance ?:
            createDatabase(context).also{
                instance = it
            }
        }


        private fun createDatabase(context: Context): NoteDatabase {
            return TODO("Provide the return value")
        }
    }
}