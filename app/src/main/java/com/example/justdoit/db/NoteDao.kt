package com.example.justdoit.db

import androidx.room.*
import com.example.justdoit.db.Note


@Dao
interface NoteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM note")
    suspend fun getAll():List<Note>

    @Delete
    suspend fun delete(note: Note)
}