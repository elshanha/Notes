package com.example.notes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notes.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE :query OR noteContent LIKE :query ORDER BY ID DESC")
    fun searchNote(query: String?) : LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE isfavorite = 1 ORDER BY ID DESC")
    fun getFavorites() : LiveData<List<Note>>
}