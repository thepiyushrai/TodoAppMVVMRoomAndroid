package com.gtappdevelopers.todoNoteapplication.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gtappdevelopers.data.model.Note
import com.gtappdevelopers.todoNoteapplication.adapter.NoteRecVAdapter
import com.gtappdevelopers.todoNoteapplication.R
import com.gtappdevelopers.data.viewmodel.ToDoNoteViewModal
import com.gtappdevelopers.todoNoteapplication.databinding.ActivityMainBinding


class HomeActivity : AppCompatActivity(), NoteRecVAdapter.NoteClickInterface,
    NoteRecVAdapter.OnClickNoteDeleteInterface {
    lateinit var viewModal: ToDoNoteViewModal
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
            initViewModel()
            setAdapter()
           onClickListner()

    }

    private fun onClickListner() {
        binding.idFAB.setOnClickListener {
            val intent = Intent(this@HomeActivity, EditMyNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    private fun setAdapter() {
        binding.notesRV.layoutManager = LinearLayoutManager(this)
        val noteRecVAdapter = NoteRecVAdapter(this, this, this)
        binding.notesRV.adapter = noteRecVAdapter

        //on below line we are calling all notes methof from our view modal class to observer the changes on list.
        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                //on below line we are updating our list.
                noteRecVAdapter.updateList(it)
            }
        })
    }

    private fun initViewModel() {
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ToDoNoteViewModal::class.java)
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@HomeActivity, EditMyNoteActivity::class.java)
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("mnoteId", note.id)
        intent.putExtra("noteType", "Edit")
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        viewModal.deleteMyNote(note)
        Toast.makeText(this, "${note.noteTitle} Note has been Deleted", Toast.LENGTH_LONG).show()
    }

}