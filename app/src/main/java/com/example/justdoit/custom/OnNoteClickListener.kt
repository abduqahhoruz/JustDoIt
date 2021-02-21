package com.example.justdoit.custom

import com.example.justdoit.db.Note

interface OnNoteClickListener {
    fun onNoteClicked(note : Note)
}