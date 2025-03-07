package com.example.unsplash.ui.photos.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.unsplash.data.model.Photo
import com.example.unsplash.databinding.ItemPhotoBinding

class PhotosRvAdapter(
    private val onItemClick: (Photo) -> Unit
) : RecyclerView.Adapter<PhotosRvAdapter.ViewHolder>() {

    private val items = mutableListOf<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount() = items.size

    fun updateList(newItems: List<Photo>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Photo, onItemClick: (Photo) -> Unit) {
            binding.apply {
                root.setOnClickListener { onItemClick(item) }

                setImage(item)
                tvTitle.text = item.description
                tvAuthor.text = item.authorName
            }
        }

        private fun setImage(item: Photo) {
            Glide.with(binding.root)
                .load(item.uriThumb)
                .into(binding.ivPhoto)
        }

    }

}

class MyDiffCallback(
    private val oldList: List<Photo>,
    private val newList: List<Photo>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}