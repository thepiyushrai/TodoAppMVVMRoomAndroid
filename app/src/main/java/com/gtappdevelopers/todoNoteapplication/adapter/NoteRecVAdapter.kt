package com.gtappdevelopers.todoNoteapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gtappdevelopers.data.model.Note
import com.gtappdevelopers.todoNoteapplication.R
import com.gtappdevelopers.todoNoteapplication.databinding.TodoNoteRvItemBinding

class NoteRecVAdapter(
    val context: Context,
    val noteClickDeleteInterface: OnClickNoteDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<NoteRecVAdapter.ViewHolder>() {

    private val myAllNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = TodoNoteRvItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    interface OnClickNoteDeleteInterface {
        fun onDeleteIconClick(note: Note)
    }

    interface NoteClickInterface {
        fun onNoteClick(note: Note)
    }

    override fun getItemCount(): Int {
        return myAllNotes.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.idTVNote.setText(myAllNotes.get(position).noteTitle)
        holder.binding.idTVDate.setText(" Updated : "+myAllNotes.get(position).timeStamp)
        holder.binding.idIVDelete.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(myAllNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(myAllNotes.get(position))
        }
    }
    fun updateList(UpdatednewList: List<Note>) {
        myAllNotes.clear()
        myAllNotes.addAll(UpdatednewList)
        notifyDataSetChanged()
    }

}

