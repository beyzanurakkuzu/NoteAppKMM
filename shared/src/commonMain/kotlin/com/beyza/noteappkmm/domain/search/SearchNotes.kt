package com.beyza.noteappkmm.domain.search

import com.beyza.noteappkmm.domain.note.Note
import com.beyza.noteappkmm.domain.time.DateTimeUtil

class SearchNotes {

    fun execute(notes:List<Note>, query:String):List<Note>
    {
        if (query.isBlank()){
            return notes
        }
        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) || it.context.trim().lowercase().contains(query.lowercase())
        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.created)
        }
    }
}