package com.beyza.noteappkmm.data.datasource

import com.beyza.noteappkmm.data.mapper.toNote
import com.beyza.noteappkmm.database.NoteDatabase
import com.beyza.noteappkmm.domain.note.Note
import com.beyza.noteappkmm.domain.datasource.NoteDataSource
import com.beyza.noteappkmm.domain.time.DateTimeUtil

class SqlDelightDataSource(db: NoteDatabase) : NoteDataSource {
    private val queries = db.noteQueries
    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            context = note.context,
            colorHex = note.colorHex,
            created = DateTimeUtil.toEpochMillis(note.created)

        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getnoteById(id)
            .executeAsOneOrNull()
            ?.toNote()

    }

    override suspend fun getAllNotes(): List<Note> {
        return  queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}