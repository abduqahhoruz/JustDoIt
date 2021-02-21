package com.example.justdoit.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.justdoit.db.AppDB
import com.example.justdoit.db.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    val mainModel by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<List<Note>>() }
    private val noteDao = AppDB.getInstance(application).notesDao()

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

    fun getAllNote() {
        Log.d("TAG", "getAllNote: ")
        GlobalScope.launch {
            val newData = noteDao.getAll()
            GlobalScope.launch(Dispatchers.Main) {
                mainModel.value = newData
            }
        }

    }

    fun sortNote() {
        GlobalScope.launch {
            val newSortedList = noteDao.order()
            GlobalScope.launch(Dispatchers.Main) {
                mainModel.value = newSortedList
            }
        }

    }
}