package com.anupras.apl.warehouseapplicationproject.ui.searchFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.anupras.apl.warehouseapplicationproject.data.Results
import com.anupras.apl.warehouseapplicationproject.databinding.ItemWarehouseProductBinding

class WarehouseSearchProductAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Results, WarehouseSearchProductAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        val binding =
            ItemWarehouseProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemWarehouseProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Results) {
            binding.apply {
                Glide.with(itemView)
                    .load(product.Products[0].ImageURL)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(productImage)

                productDescription.text = product.Products[0].Description
            }
        }


        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results) =
                oldItem.Products[0].ImageURL == newItem.Products[0].ImageURL

            override fun areContentsTheSame(oldItem: Results, newItem: Results) =
                oldItem == newItem
        }
    }
}