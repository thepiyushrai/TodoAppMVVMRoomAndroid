package com.gtappdevelopers.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gtappdevelopers.data.model.Note

@Dao
interface ToDoNotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>


}