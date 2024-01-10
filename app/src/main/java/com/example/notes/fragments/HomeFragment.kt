package com.example.notes.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.R
import com.example.notes.adapter.NoteAdapter
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.model.Note
import com.example.notes.viewmodels.NoteViewModel
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private var homeBinding : FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private val notesViewModel : NoteViewModel by viewModels()
    private lateinit var noteAdapter: NoteAdapter

    private lateinit var homeToolbar: MaterialToolbar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHomeRecycleView()

        binding.addNoteFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }



        homeToolbar = binding.homeToolbar

        homeToolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.favorites -> {
                    findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)
                true
                } else -> false
            }
        }



        val menuSearch = homeToolbar.menu.findItem(R.id.searchMenu).actionView as SearchView


            menuSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        searchNote(newText)
                    }
                    return true
                }

            })

        }

    private fun updateUI(note: List<Note>?) {
        if (note != null) {
            if (note.isNotEmpty()) {
                binding.lottieEmptyNote.visibility = View.GONE
                binding.homeRecyclerView.visibility = View.VISIBLE
            } else {
                binding.lottieEmptyNote.visibility = View.VISIBLE
                binding.homeRecyclerView.visibility = View.GONE
            }
        }
    }

    private fun setupHomeRecycleView() {
        noteAdapter = NoteAdapter(notesViewModel, this)
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            notesViewModel.getAllNotes().observe(viewLifecycleOwner) {note ->
                noteAdapter.differ.submitList(note)
                updateUI(note)
            }
        }
    }

    private fun searchNote(query: String?) {
        val searchQuery = "%$query%"

        notesViewModel.searchNote(searchQuery).observe(viewLifecycleOwner) {
            noteAdapter.differ.submitList(it)

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        homeBinding = null
    }


}
