package com.paligot.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paligot.home.BuildConfig
import com.paligot.style.databinding.ListItemMediaBinding
import com.squareup.picasso.Picasso

class MediaListAdapter : ListAdapter<MediaUi, MediaListAdapter.ViewHolder>(MediaListDiff()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(ListItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    holder.apply {
      bind(item)
      itemView.tag = item
    }
  }

  class ViewHolder(private val binding: ListItemMediaBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MediaUi) {
      binding.apply {
        Picasso.get().apply {
          isLoggingEnabled = BuildConfig.DEBUG
          load(item.pictureUrl).into(mediaImageView)
        }
        titleTextView.text = item.title
      }
    }
  }
}

class MediaListDiff : DiffUtil.ItemCallback<MediaUi>() {
  override fun areItemsTheSame(oldItem: MediaUi, newItem: MediaUi): Boolean = oldItem.title == newItem.title

  override fun areContentsTheSame(oldItem: MediaUi, newItem: MediaUi): Boolean = oldItem == newItem
}