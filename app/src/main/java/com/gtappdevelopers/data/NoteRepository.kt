package com.gtappdevelopers.data

import androidx.lifecycle.LiveData
import com.gtappdevelopers.data.model.Note

class NoteRepository(private val toDoNotesDao: ToDoNotesDao) {


    val allNotes: LiveData<List<Note>> = toDoNotesDao.getAllNotes()
//insert
    suspend fun insert(note: Note) {
        toDoNotesDao.insert(note)
    }
    //delete
    suspend fun delete(note: Note){
        toDoNotesDao.delete(note)
    }
//update
    suspend fun update(note: Note){
        toDoNotesDao.update(note)
    }
}