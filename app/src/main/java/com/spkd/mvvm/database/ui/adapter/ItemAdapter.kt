package com.spkd.mvvm.database.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.spkd.mvvm.database.data.entitiy.Item


// RecyclerView Adapter
class ItemAdapter(private val onDelete: (Item) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnLongClickListener {
            onDelete(item)
            true
        }
    }

    class ItemViewHolder(view: android.view.View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun bind(item: Item) {
            (itemView as TextView).text = item.name
        }
    }

    class ItemDiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }
}