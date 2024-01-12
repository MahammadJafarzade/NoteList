package com.mahammadjafarzade.authenticationfirebase.features.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahammadjafarzade.authenticationfirebase.databinding.ListItemHeaderBinding
import com.mahammadjafarzade.authenticationfirebase.databinding.ListItemNoteBinding
import com.mahammadjafarzade.authenticationfirebase.model.NoteEntity
import com.mahammadjafarzade.authenticationfirebase.util.format
import java.util.Date

class NoteListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val HEADER = 1
    val ITEM = 2

    var newList = mutableListOf<Any>()
    var noteList = listOf<NoteEntity>()

    fun setList(list : List<NoteEntity>) {
        this.noteList = list

        newList.clear()
        val groupedList = noteList.groupBy { it.date }

        groupedList.forEach {key, value ->
            newList.add(key)
            newList.addAll(value)

        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val item = newList.get(position)
        when(item) {
            is Long -> return HEADER
            else -> return ITEM
        }
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder : RecyclerView.ViewHolder? = null

        when(viewType) {
            HEADER -> {
                var binding = ListItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = HeaderViewHolder(binding)
            }

            ITEM -> {
                var binding = ListItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = NoteViewHolder(binding)
            }
        }

        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType)  {
            HEADER -> {
                val viewHolder = holder as HeaderViewHolder
                val item = newList.get(position) as Long
                val date = Date(item)
                viewHolder.bind(date.format())
            }

            ITEM-> {
                val viewHolder = holder as NoteViewHolder
                val item = newList.get(position) as NoteEntity
                viewHolder.bind(item)
            }
        }
    }
}

class NoteViewHolder(val binding : ListItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(note : NoteEntity) {
        binding.txtNote.text = note.title
        binding.txtNoteDetail.text = note.detail
    }
}

class HeaderViewHolder(val binding : ListItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(title : String) {
        binding.txtNote.text = title
    }
}