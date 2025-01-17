package com.example.architecture_components.Dao

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val date: Date?,
    val text: String?
) {
    @Ignore
    constructor(date: Date, text: String) : this(0, date, text)

    @Ignore
    constructor() : this(0, null, null)
}