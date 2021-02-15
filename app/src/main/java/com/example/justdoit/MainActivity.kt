package com.example.justdoit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.justdoit.viewmodel.MainActivityViewModel

lateinit var viewModel: MainActivityViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        subscribe()
        getAllNote()
    }

    fun subscribe() {
        viewModel.mainModel.observe(this, Observer {

        })
    }

    fun addNote() {

    }

    fun deleteNote(){

    }

    fun updateNote(){

    }

    fun getAllNote(){
        viewModel.getAllNote()
    }
}