package com.example.justdoit.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.justdoit.R
import com.example.justdoit.db.Note
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

class NoteAdapter(
    val onClick: (note: Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.VH>() {

    private val list: MutableList<Note> = mutableListOf()
    fun setList(newList: List<Note>) {
        Log.d("TAG", "setList: ")
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: VH, position: Int) {
        val it = list[position]
        holder.onBind(it)
    }

    override fun getItemCount(): Int = list.size

    inner class VH(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun onBind(note: Note) {
            tv_title.text = note.title
            tv_desc.text = note.description
            containerView.setOnClickListener {
                onClick(note)
            }

        }
    }
}
