package com.paligot.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.paligot.home.BuildConfig
import com.paligot.shared.movies.Movie
import com.paligot.style.databinding.ListItemMediaBinding
import com.squareup.picasso.Picasso

class MediaListAdapter : PagedListAdapter<Movie, MediaListAdapter.ViewHolder>(MediaListDiff()) {
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
    fun bind(item: Movie?) {
      item?.let {
        binding.apply {
          Picasso.get().apply {
            isLoggingEnabled = BuildConfig.DEBUG
            load(item.posterUrl).into(mediaImageView)
          }
          titleTextView.text = item.title
          executePendingBindings()
        }
      }
    }
  }
}

class MediaListDiff : DiffUtil.ItemCallback<Movie>() {
  override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.title == newItem.title

  override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
}