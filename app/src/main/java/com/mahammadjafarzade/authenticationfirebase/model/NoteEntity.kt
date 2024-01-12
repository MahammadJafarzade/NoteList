package com.mahammadjafarzade.authenticationfirebase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val uid : Int = 0,
    @ColumnInfo(name = "title") val title : String?,
    @ColumnInfo(name = "note_detail") val detail : String?,
    @ColumnInfo(name = "note_tag") val tag : String?,
    @ColumnInfo(name = "noteDate") val date : Long = 0
) {
}