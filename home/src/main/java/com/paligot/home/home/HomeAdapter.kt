package com.paligot.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paligot.home.R
import com.paligot.shared.extensions.getOf
import com.paligot.shared.extensions.indexOf
import com.paligot.shared.movies.*
import com.paligot.style.Direction
import com.paligot.style.MarginItemDecoration
import com.paligot.style.databinding.ListItemMediaLineBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
  private val original = mutableMapOf<Int, MediaListUi>()

  fun addPopular(populars: MediaListUi) = add(POPULAR, populars)
  fun addTrending(trending: MediaListUi) = add(TRENDING, trending)
  fun addUpComing(trending: MediaListUi) = add(UP_COMING, trending)
  fun addTopSelling(trending: MediaListUi) = add(TOP_SELLING, trending)
  fun addRecommendations(recommendations: MediaListUi) = add(RECOMMENDATION, recommendations)

  fun removeRecommendations() = remove(RECOMMENDATION)

  private fun remove(tag: Int) {
    if (original.containsKey(tag).not()) return
    val position = original.indexOf(tag)
    original.remove(tag)
    notifyItemRemoved(position)
  }

  private fun add(tag: Int, medias: MediaListUi) {
    if (medias.medias.size == 0) return
    val has = original.containsKey(tag)
    original[tag] = medias
    val sorted = original.toSortedMap()
    val position = sorted.indexOf(tag)
    original.clear()
    original.putAll(sorted)
    if (has) notifyItemChanged(position)
    else notifyItemInserted(position)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(ListItemMediaLineBinding.inflate(LayoutInflater.from(parent.context), parent, false))

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = original.getOf(position)
    holder.apply {
      bind(item)
      itemView.tag = item
    }
  }

  override fun getItemCount(): Int = original.size

  class ViewHolder(private val binding: ListItemMediaLineBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mediaList: MediaListUi) {
      binding.apply {
        titleTextView.text = mediaList.title
        val adapter = MediaListAdapter()
        mediaRecyclerView.adapter = adapter
        mediaRecyclerView.setHasFixedSize(true)
        mediaRecyclerView.addItemDecoration(
          MarginItemDecoration(
            binding.root.resources.getDimensionPixelSize(R.dimen.list_media_spacing),
            Direction.HORIZONTAL
          )
        )
        adapter.submitList(mediaList.medias)
        executePendingBindings()
      }
    }
  }
}
