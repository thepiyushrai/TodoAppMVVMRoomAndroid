package com.gtappdevelopers.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gtappdevelopers.data.model.Note
import com.gtappdevelopers.data.NoteRepository
import com.gtappdevelopers.data.ToDoNoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoNoteViewModal(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    init {
        val dao = ToDoNoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun addNewNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun deleteMyNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }


}