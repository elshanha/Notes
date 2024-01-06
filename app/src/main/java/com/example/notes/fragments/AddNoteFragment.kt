package com.example.notes.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNoteBinding
import com.example.notes.viewmodels.NoteViewModel
import com.example.notes.model.Note
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment(R.layout.fragment_add_note) {

    private var addNoteBinding : FragmentAddNoteBinding? = null
    private val binding get() = addNoteBinding!!

    val notesViewModel : NoteViewModel by viewModels()
    lateinit var addNoteView  :View

    lateinit var addNoteToolbar : MaterialToolbar



    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        addNoteView = view

        addNoteToolbar = binding.addNoteToolbar

        addNoteToolbar.setOnMenuItemClickListener {menuItem->
            when(menuItem.itemId) {
                R.id.saveMenu -> {
                    saveNote(addNoteView)
                    true
                }
                else -> false
            }

        }

        addNoteToolbar.setNavigationOnClickListener {
            view.findNavController().navigate(R.id.homeFragment)
        }
    }

    private fun saveNote(view: View) {
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteContent = binding.addNoteContent.text.toString().trim()

        if (noteContent.isNotEmpty()) {
            val note = Note(0, noteTitle, noteContent, notesViewModel.currentTimeMillis.toString(), false)
            notesViewModel.addNote(note)

            Toast.makeText(addNoteView.context, notesViewModel.saveInfoText, Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.homeFragment)
        } else {
            Toast.makeText(addNoteView.context, notesViewModel.warningText, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding = null
    }
}