package com.mahammadjafarzade.authenticationfirebase.features.newnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahammadjafarzade.authenticationfirebase.model.NoteEntity
import com.mahammadjafarzade.authenticationfirebase.repository.NotesRepository
import com.mahammadjafarzade.authenticationfirebase.util.format
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddNewNoteViewModel @Inject constructor(val repository: NotesRepository): ViewModel() {
    var noteTitle = MutableLiveData<String>()
    var noteDetail = MutableLiveData<String>()
    var noteDateText = MutableLiveData<String>()
    var noteDateValue : Date = Date()
    var noteTag = MutableLiveData<String>()

    var onSaveCompleted = MutableLiveData<Boolean>(false)
    var onShowDatePickerTapped = MutableLiveData<Boolean>(false)

    fun onAddButtonClick() {
        if(isValid()) {
            insertNote()
            onSaveCompleted.postValue(true)
        }
    }

    fun onShowDatePicker() {
        onShowDatePickerTapped.postValue(true)
    }

    fun userSelectDate(date : Long) {
        noteDateValue = Date(date)
        noteDateText.postValue(noteDateValue.format())
    }
    private fun isValid() : Boolean {
        return !(noteTitle.value.isNullOrEmpty() || noteDetail.value.isNullOrEmpty())
    }


    private fun insertNote() {
        val note = NoteEntity(
            title = noteTitle.value,
            detail = noteDetail.value,
            date = noteDateValue.time,
            tag = noteTag.value)
        viewModelScope.launch {
            repository.insert(note)
        }
    }
}