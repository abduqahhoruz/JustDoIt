package com.example.justdoit.db

import androidx.room.*


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    suspend fun order(): List<Note>
}