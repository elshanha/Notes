package com.example.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.R
import com.example.notes.adapter.NoteAdapter
import com.example.notes.databinding.FragmentFavoritesBinding
import com.example.notes.model.Note
import com.example.notes.viewmodels.NoteViewModel
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var favoritesBinding : FragmentFavoritesBinding? = null
    private val binding get() = favoritesBinding!!
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var favToolbar: MaterialToolbar

    private val notesViewModel : NoteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        favoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHomeRecycleView()

        favToolbar = binding.favToolbar

        favToolbar.setNavigationOnClickListener {
            view.findNavController().navigate(R.id.homeFragment)
        }

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
            notesViewModel.getFavorites().observe(viewLifecycleOwner) {note ->
                noteAdapter.differ.submitList(note)
                updateUI(note)
            }
        }
    }

}