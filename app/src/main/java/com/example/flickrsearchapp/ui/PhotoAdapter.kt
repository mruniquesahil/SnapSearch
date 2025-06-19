package com.example.flickrsearchapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrsearchapp.Model.Photo
import com.example.flickrsearchapp.databinding.LayoutItemBinding
import com.squareup.picasso.Picasso

/**
 * Adapter class to load the images
 */
class PhotoAdapter(
    private val mList: List<Photo>,
    private val onImageClickListener: OnImageClickListener
) : RecyclerView.Adapter<PhotoAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.MainViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class MainViewHolder(val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: Photo) {
            Picasso.get().load(itemViewModel.url_s).into(binding.image)
            binding.title.text = itemViewModel.title
            binding.image.setOnClickListener { view ->
                onImageClickListener.onImageClick(itemViewModel.url_s)
            }
        }
    }

    interface OnImageClickListener {
        fun onImageClick(url: String)
    }

}