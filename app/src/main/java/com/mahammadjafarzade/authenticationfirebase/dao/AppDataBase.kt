package com.mahammadjafarzade.authenticationfirebase.dao

import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import com.mahammadjafarzade.authenticationfirebase.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1,)
abstract class Appdatabase : RoomDatabase() {
    abstract fun noteDao() : NotesDao

}