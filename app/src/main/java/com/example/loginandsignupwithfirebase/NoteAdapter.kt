package com.example.loginandsignupwithfirebase

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandsignupwithfirebase.databinding.NotesItemBinding

class NoteAdapter(private val note : List<Noteitem>,private val itemCLickListener : OnItemClickListener) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    interface OnItemClickListener{
        fun onDeleteClick(noteId : String)
        fun onUpdateClick(noteId : String , title : String , description : String)
    }
    class NoteViewHolder(val binding: NotesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Noteitem) {
            binding.titleTextView.text = note.title
            binding.descriptionTextView.text = note.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return note.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = note[position]
        holder.bind(note)
        holder.binding.updateButton.setOnClickListener {
            itemCLickListener.onUpdateClick(note.noteId, note.title , note.description)
        }
        holder.binding.deleteButton.setOnClickListener {
            itemCLickListener.onDeleteClick(note.noteId)
        }
    }
}


