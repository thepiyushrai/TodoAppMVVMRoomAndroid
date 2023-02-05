package com.gtappdevelopers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gtappdevelopers.data.model.Note

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class ToDoNoteDatabase : RoomDatabase() {

    abstract fun getNotesDao(): ToDoNotesDao

    companion object {

        @Volatile
        private var MINSTANCE: ToDoNoteDatabase? = null

        fun getDatabase(context: Context): ToDoNoteDatabase {
            return MINSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoNoteDatabase::class.java,
                    "my_todo_note_database"
                ).build()
                MINSTANCE = instance
                instance
            }
        }
    }


}