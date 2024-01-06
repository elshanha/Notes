package com.example.notes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.model.Note
import com.example.notes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor (app: Application, private val noteRepository: NoteRepository) :
    AndroidViewModel(app) {

    val currentTimeMillis = System.currentTimeMillis().toString()


        val favInfoText = "This action is not allowed in Favorites"
        val saveInfoText = "Note Saved"
        val warningText = "Please add Note"
        val editNoteInfoText = "Note edited"

        fun addNote(note: Note) =
            viewModelScope.launch {
                noteRepository.insertNote(note)
            }

        fun deleteNote(note: Note) =
            viewModelScope.launch {
                noteRepository.deleteNote(note)
            }

        fun updateNote(note: Note) =
            viewModelScope.launch {
                noteRepository.updateNote(note)
            }

        fun getAllNotes() = noteRepository.getAllNotes()

        fun searchNote(query: String?) = noteRepository.searchNote(query)

        fun getFavorites() = noteRepository.getFavorites()

    }
