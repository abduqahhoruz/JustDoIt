package com.example.justdoit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.justdoit.adapter.NoteAdapter
import com.example.justdoit.db.Note
import com.example.justdoit.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

lateinit var viewModel: MainActivityViewModel
lateinit var adapter: NoteAdapter

class MainActivity : AppCompatActivity() {

     private var note: Note? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        adapter = NoteAdapter {
            note = it
            btn.text = "EDIT"
            editTitle.setText(it.title)
            editDesc.setText(it.description)
        }
        recycler.adapter = adapter

        subscribe()
        getAllNote()

        btn.setOnClickListener {
            if (btn.text == "EDIT") {
                val newNote = note?.copy(title = editTitle.text.toString(), description = editDesc.text.toString())
                updateNote(newNote!!)
                editTitle.setText("")
                editDesc.setText("")
                btn.text = "Add"
            }
            else
                addNote(
                    editTitle.text.toString(),
                    editDesc.text.toString()
                )
            editTitle.setText("")
            editDesc.setText("")

        }
        btnClear.setOnClickListener{
            editTitle.setText("")
            editDesc.setText("")
            btn.text = "Add"
        }
         btnSort.setOnClickListener{
             viewModel.sortNote()
         }
    }

    private fun subscribe() {
        viewModel.mainModel.observe(this, {
            adapter.setList(it)
        })
    }

    private fun addNote(title: String, desc: String) {
        val note = Note(System.currentTimeMillis(), title, desc)
        viewModel.addNote(note)
    }

    fun deleteNote() {

    }

    private fun updateNote(note: Note) {
        viewModel.updateNote(note)
    }

    private fun getAllNote() {
        viewModel.getAllNote()
    }
}