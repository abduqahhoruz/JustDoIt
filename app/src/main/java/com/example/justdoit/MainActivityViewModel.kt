package com.example.justdoit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.justdoit.db.AppDB
import com.example.justdoit.db.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    val mainModel = MutableLiveData<List<Note>>()
    val noteDao = AppDB.getInstance(application).notesDao()


    fun addNote(note: Note) {
        GlobalScope.launch {
            noteDao.insertNote(note)
            val newData = noteDao.getAll()
            GlobalScope.launch(Dispatchers.Main) {
                mainModel.value = newData
            }
        }
    }

    fun updateNote(note: Note) {
        GlobalScope.launch {
            noteDao.update(note)
            val newData = noteDao.getAll()
            GlobalScope.launch(Dispatchers.Main) {
                mainModel.value = newData
            }
        }


    }

    fun deleteNote(note: Note) {
        GlobalScope.launch {
            noteDao.delete(note)
            val newData = noteDao.getAll()
            GlobalScope.launch(Dispatchers.Main) {
                mainModel.value = newData
            }
        }

    }

    fun getAll() {
        GlobalScope.launch {
            val newData = noteDao.getAll()
            GlobalScope.launch(Dispatchers.Main) {
                mainModel.value = newData
            }
        }

    }
}