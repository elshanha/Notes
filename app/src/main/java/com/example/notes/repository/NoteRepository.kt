package com.example.notes.repository

import com.example.notes.database.NoteDatabase
import com.example.notes.model.Note
import javax.inject.Inject


class NoteRepository @Inject constructor(private val db: NoteDatabase) {

    suspend fun insertNote(note: Note) = db.getNoteDao().insert(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)
    fun getFavorites() = db.getNoteDao().getFavorites()

}