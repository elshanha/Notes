package com.example.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.NoteLayoutBinding
import com.example.notes.fragments.FavoritesFragment
import com.example.notes.fragments.HomeFragment
import com.example.notes.fragments.HomeFragmentDirections
import com.example.notes.model.Note
import com.example.notes.viewmodels.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteAdapter(private val viewModel: NoteViewModel, private val currentFragment: Fragment) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: NoteLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteContent == newItem.noteContent &&
                    oldItem.noteTitle == newItem.noteTitle &&
                    oldItem.timestamp == newItem.timestamp
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {


        val currentNote = differ.currentList[position]

        currentNote.timestamp =
            SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())

        holder.binding.noteTitle.text = currentNote.noteTitle
        holder.binding.noteContent.text = currentNote.noteContent
//            holder.binding.noteDate.text = currentNote.timestamp

        holder.itemView.setOnClickListener {

            if (currentFragment is HomeFragment) {
                val direction =
                    HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
                it.findNavController().navigate(direction)
            } else if (currentFragment is FavoritesFragment) {
                Toast.makeText(currentFragment.context, viewModel.favInfoText, Toast.LENGTH_SHORT)
                    .show()
            }

        }

        val favoriteIconColor = if (currentNote.isFavorite) {
            ContextCompat.getColor(holder.itemView.context, R.color.forest_green)
        } else {
            ContextCompat.getColor(holder.itemView.context, R.color.grey)
        }
        holder.binding.cardFavButton.setColorFilter(favoriteIconColor)


        holder.binding.cardFavButton.setOnClickListener {
            currentNote.isFavorite = !currentNote.isFavorite

            if (currentNote.isFavorite && (currentNote.id == position)) {
                notifyItemChanged(currentNote.id)
                viewModel.updateNote(currentNote)
            } else {
                notifyItemChanged(position)
                viewModel.updateNote(currentNote)
            }

        }


    }
}