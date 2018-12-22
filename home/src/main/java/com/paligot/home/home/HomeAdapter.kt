package com.paligot.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paligot.home.databinding.ListItemMediaLineBinding

class HomeAdapter : ListAdapter<MediaListUi, HomeAdapter.ViewHolder>(HomeDiff()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      ListItemMediaLineBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    holder.apply {
      bind(item)
      itemView.tag = item
    }
  }

  class ViewHolder(private val binding: ListItemMediaLineBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mediaList: MediaListUi) {
      binding.apply {
        titleTextView.text = mediaList.title
        val adapter = MediaListAdapter()
        mediaRecyclerView.adapter = adapter
        adapter.submitList(mediaList.medias)
      }
    }
  }
}

class HomeDiff : DiffUtil.ItemCallback<MediaListUi>() {
  override fun areItemsTheSame(oldItem: MediaListUi, newItem: MediaListUi): Boolean {
    return oldItem.title == newItem.title
  }

  override fun areContentsTheSame(oldItem: MediaListUi, newItem: MediaListUi): Boolean {
    return oldItem == newItem
  }
}