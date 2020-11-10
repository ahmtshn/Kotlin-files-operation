package com.ahmetsahin.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var list: MutableList<Model>, private var onClickInterface: Listener) :
    RecyclerView.Adapter<Adapter.FViewHolder>() {

    inner class FViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fileName: TextView = itemView.findViewById(R.id.tv_file_name)
        val modify: TextView = itemView.findViewById(R.id.tv_file_modify)
        private val deleteText: TextView = itemView.findViewById(R.id.tv_delete)

        init {
            deleteText.setOnClickListener {
                onClickInterface.setClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FViewHolder {
        return FViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FViewHolder, position: Int) {
        holder.fileName.text = list[position].fileName
        holder.modify.text = list[position].modifyTime
    }

    override fun getItemCount() = list.size
}