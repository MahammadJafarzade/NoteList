package com.mahammadjafarzade.authenticationfirebase.features.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mahammadjafarzade.authenticationfirebase.model.NoteEntity
import com.mahammadjafarzade.authenticationfirebase.repository.NotesRepository
import com.mahammadjafarzade.authenticationfirebase.repository.ResourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository,
    val resourceRepository: ResourceRepository) : ViewModel() {

    fun getAllData() : LiveData<List<NoteEntity>> {
        return repository.getAll()
    }

}