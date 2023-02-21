package com.gtappdevelopers.todoNoteapplication.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gtappdevelopers.data.model.Note
import com.gtappdevelopers.todoNoteapplication.R
import com.gtappdevelopers.data.viewmodel.ToDoNoteViewModal
import com.gtappdevelopers.todoNoteapplication.databinding.ActivityAddEditNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class EditMyNoteActivity : AppCompatActivity() {
    private var noteType: String? = null
    lateinit var binding: ActivityAddEditNoteBinding
    lateinit var viewModal: ToDoNoteViewModal
    var mNoteID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_edit_note)
       initviewModal()
        initView()
       onClickListner()
    }

    private fun onClickListner() {
        noteType = intent.getStringExtra("noteType")

        binding.idBtn.setOnClickListener {
            val noteTitle = binding.idEdtNoteName.text.toString()
            val noteDescription = binding.idEdtNoteDesc.text.toString()
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.id = mNoteID
                    viewModal.updateNote(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    viewModal.addNewNote(Note(noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            this.finish()
        }
    }

    private fun initView() {
         noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            mNoteID = intent.getIntExtra("mnoteId", -1)
            binding.idBtn.text = "Update My Note"
            binding.idEdtNoteName.setText(noteTitle)
            binding.idEdtNoteDesc.setText(noteDescription)
        } else {
            binding.idBtn.text = "Save My Note"
        }
    }

    private fun initviewModal() {
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ToDoNoteViewModal::class.java)
    }
}