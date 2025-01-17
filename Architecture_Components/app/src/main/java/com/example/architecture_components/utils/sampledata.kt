package com.example.architecture_components.utils

import com.example.architecture_components.Dao.NoteEntity
import java.util.Calendar
import java.util.Date

class sampledata {
    val s1="string 1"
    val s2="string 2"
    val s3="string 3"

    public fun getdate(diffamount :Int): Date {
        val calander=Calendar.getInstance()
        calander.add(Calendar.MILLISECOND,diffamount)
        return calander.time
    }
    public fun getSampleData() : List<NoteEntity>{
        val noteList= mutableListOf<NoteEntity>()
        noteList.add(NoteEntity(1,getdate(0),s1))
        noteList.add(NoteEntity(2,getdate(-1),s2))
        noteList.add(NoteEntity(3,getdate(-2),s3))
        return noteList
    }
}