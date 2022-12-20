package com.beyza.noteappkmm.data.mapper

import com.beyza.noteappkmm.domain.note.Note
import database.note.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        context=context,
        colorHex=colorHex,
        created= Instant.fromEpochMilliseconds(created)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}