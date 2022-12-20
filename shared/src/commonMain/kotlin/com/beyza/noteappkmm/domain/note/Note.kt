package com.beyza.noteappkmm.domain.note

import com.beyza.noteappkmm.presentation.*
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val context: String,
    val colorHex: Long,
    val created: LocalDateTime
) {
    companion object {
        private val colors = listOf(
            RedOrangeHex, RedPinkHex, LightGreenHex,
            BabyBlueHex, VioletHex
        )
        fun generateRandomColor() = colors.random()
    }
}