package com.mahammadjafarzade.authenticationfirebase.repository

import androidx.lifecycle.LiveData
import com.mahammadjafarzade.authenticationfirebase.dao.NotesDao
import com.mahammadjafarzade.authenticationfirebase.model.NoteEntity
import javax.inject.Inject

interface NoteRepositoryInterface {
    suspend fun insert(note : NoteEntity)
    fun getAll() : LiveData<List<NoteEntity>>
}

class NotesRepository @Inject constructor(private val noteDao : NotesDao)  : NoteRepositoryInterface {
    override suspend fun insert(note: NoteEntity) {
        noteDao.insert(note)
    }

    override fun getAll(): LiveData<List<NoteEntity>> {
        return noteDao.getAll()
    }
}