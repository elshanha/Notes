package com.example.notes.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.databinding.FragmentEditNoteBinding
import com.example.notes.viewmodels.NoteViewModel
import com.example.notes.model.Note
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    private var editNoteBinding : FragmentEditNoteBinding? = null
    private val binding get() = editNoteBinding!!

    private val notesViewModel : NoteViewModel by viewModels()
    private lateinit var currentNote : Note

    private lateinit var editNoteToolBar : MaterialToolbar

    private val args: EditNoteFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        editNoteBinding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentNote = args.note!!


        binding.editNoteTitle.setText(currentNote.noteTitle)
        binding.editNoteContent.setText(currentNote.noteContent)

        binding.editNoteFab.setOnClickListener{
            val noteTitle = binding.editNoteTitle.text.toString().trim()
            val noteContent = binding.editNoteContent.text.toString().trim()

                val note = Note(currentNote.id, noteTitle, noteContent, notesViewModel.currentTimeMillis, false)
                notesViewModel.updateNote(note)
                view.findNavController().navigate(R.id.homeFragment)
                Toast.makeText(context, notesViewModel.editNoteInfoText, Toast.LENGTH_SHORT).show()
        }

        editNoteToolBar = binding.editNoteToolbar

        editNoteToolBar.setOnMenuItemClickListener {menuItem ->
            when(menuItem.itemId) {
                R.id.deleteMenu -> {
                    deleteNote()
                    true
                } else -> false
            }
        }

        editNoteToolBar.setNavigationOnClickListener {
            view.findNavController().navigate(R.id.homeFragment)
        }

    }

    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Do yo want to delete this note?")
            setPositiveButton("Delete") {_,_ ->
                notesViewModel.deleteNote(currentNote)
                Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.navigate(R.id.homeFragment)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }


    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }


}