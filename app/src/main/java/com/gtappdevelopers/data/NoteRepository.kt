package com.gtappdevelopers.data

import androidx.lifecycle.LiveData
import com.gtappdevelopers.data.model.Note

class NoteRepository(private val toDoNotesDao: ToDoNotesDao) {


    val allNotes: LiveData<List<Note>> = toDoNotesDao.getAllNotes()

    suspend fun insert(note: Note) {
        toDoNotesDao.insert(note)
    }
    suspend fun delete(note: Note){
        toDoNotesDao.delete(note)
    }

    suspend fun update(note: Note){
        toDoNotesDao.update(note)
    }
}