package com.mahammadjafarzade.authenticationfirebase.features.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahammadjafarzade.authenticationfirebase.databinding.ListItemSingleTagBinding
import com.mahammadjafarzade.authenticationfirebase.databinding.ListItemTagsBinding
import com.mahammadjafarzade.authenticationfirebase.model.NoteEntity

class NoteTagAdapter : RecyclerView.Adapter<NoteTagAdapter.NoteTagViewHolder>() {
    private val noteList = mutableListOf<NoteEntity>()

    fun setList(list: List<NoteEntity>) {
        noteList.clear()
        noteList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteTagViewHolder {
        val binding = ListItemSingleTagBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteTagViewHolder, position: Int) {
        val item = noteList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    class NoteTagViewHolder(val binding: ListItemSingleTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            binding.txtTagTitle.text = note.tag
        }
    }
}