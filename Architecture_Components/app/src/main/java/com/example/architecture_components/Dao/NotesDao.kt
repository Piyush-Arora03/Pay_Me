package com.example.architecture_components.Dao

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertNote(noteEntity: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertAllNotes(noteEntity: List<NoteEntity>)

    @Delete
    fun DeleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun GetAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun GetNoteById(id: Int): NoteEntity

    @Query("DELETE FROM notes")
    fun DeleteAllNotes(): Int

    @Query("SELECT COUNT(*) FROM  notes")
    fun GetNotesCount(): Int
}