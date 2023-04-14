package com.geeks.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NoteAdapter(val iOnItem: IOnItem): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    val list: MutableList<NoteModel> = ArrayList()

    fun setList(list: MutableList<NoteModel>){
        this.list.clear()
        this.list.addAll(list)
    }

    fun delete(pos: Int){
        this.list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val imageView: ImageView = item.findViewById(R.id.item_image)
        fun bind(pos: Int){
            imageView.setOnClickListener { iOnItem.edit(adapterPosition, list[pos]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    interface IOnItem{
        fun delete(pos:Int)
        fun share(pos:Int)
        fun edit(pos:Int, note: NoteModel)
    }
}