package com.example.notes.repository

import com.example.notes.model.Note
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import javax.inject.Inject


interface FirebaseRepositoryInterface {
    fun addNote(note: Note, result: (Boolean) -> Unit)
    fun getNotes( result: (List<Note>) -> Unit)
    fun deleteNote(documentId : String, result : ()->Unit)
}

class FirebaseRepository @Inject constructor(private val db : FirebaseFirestore) : FirebaseRepositoryInterface {

    override fun addNote(note: Note, result: (Boolean) -> Unit) {
        db.collection("Notes")
            .add(note)
            .addOnSuccessListener {
                result(true)
            }
            .addOnFailureListener {
                result(false)
            }
    }

    override fun getNotes(result: (List<Note>) -> Unit) {
        db.collection("Notes")
            .get()
            .addOnSuccessListener {
                val noteList = mutableListOf<Note>()
                for (doc in it) {
                    val notes = doc.toObject(Note::class.java)
                    noteList.add(notes)
                    result(noteList)
                }
            }
            .addOnFailureListener {
                result(emptyList())
            }
    }

    override fun deleteNote(documentId: String, result: () -> Unit) {

    }
}