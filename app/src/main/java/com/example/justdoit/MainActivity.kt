package com.example.justdoit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.justdoit.adapter.NoteAdapter
import com.example.justdoit.db.Note
import com.example.justdoit.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

lateinit var viewModel: MainActivityViewModel
lateinit var adapter: NoteAdapter

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        adapter = NoteAdapter()
        recycler.adapter = adapter
        
        subscribe()
        getAllNote()

        btn.setOnClickListener{
             addNote(
                 editTitle.text.toString(),
                 editDesc.text.toString()
             )
            editTitle.setText("")
            editDesc.setText("")
        }
    }

    private fun subscribe() {
        viewModel.mainModel.observe(this, {
            adapter.setList(it)
        })
    }

    private fun addNote(title: String, desc: String) {
        val note  = Note(System.currentTimeMillis(),title,desc)
        viewModel.addNote(note)
    }

    fun deleteNote() {

    }

    fun updateNote(note: Note) {
viewModel.updateNote(note)
    }

    fun getAllNote() {
        viewModel.getAllNote()
    }
}