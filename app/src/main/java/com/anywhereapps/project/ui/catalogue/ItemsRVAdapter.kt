package com.anywhereapps.project.ui.catalogue

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.anywhereapps.project.databinding.ItemViewBinding
import com.anywhereapps.project.network.Item

import com.anywhereapps.project.util.AppUtil


class ItemsRVAdapter(
    private val itemClickedListener: OnItemClickedListener,
    val mContext : Context?) : RecyclerView.Adapter<ItemsRVAdapter.ItemViewHolder>() {

    private var items = listOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size


    fun submitData(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding : ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.titleText.text = item.name
            binding.addressText.text = "${item.lat} , ${item.lng}"
            itemView.setOnClickListener { itemClickedListener.onItemClicked(item) }
        }
    }

    interface OnItemClickedListener {
        fun onItemClicked(item: Item)
    }


}