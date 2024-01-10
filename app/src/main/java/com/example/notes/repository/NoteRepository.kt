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




//interface NoteRepositoryInterface {
//    suspend fun insertNote(note: Note)
//    suspend fun deleteNote(note: Note)
//    suspend fun updateNote(note: Note)
//
//    fun getAllNotes()
//    fun searchNote(query: String?)
//    fun getFavorites()
//}

//class NoteRepository @Inject constructor(private val db: NoteDatabase) : NoteRepositoryInterface {
//    override suspend fun insertNote(note: Note) {
//        db.getNoteDao().insert(note)
//    }
//
//    override suspend fun deleteNote(note: Note) {
//        db.getNoteDao().deleteNote(note)
//    }
//
//    override suspend fun updateNote(note: Note) {
//        db.getNoteDao().updateNote(note)
//    }
//
//    override fun getAllNotes() {
//        db.getNoteDao().getAllNotes()
//    }
//
//    override fun searchNote(query: String?) {
//        db.getNoteDao().searchNote(query)
//    }
//
//    override fun getFavorites() {
//        db.getNoteDao().getFavorites()
//    }
//
//
//}