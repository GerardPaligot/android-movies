package com.paligot.user.connected

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paligot.shared.livedata.SingleLiveEvent
import com.paligot.user.databinding.ListItemAccountBinding

class UserConnectedAdapter(items: List<SettingsUi>) :
  ListAdapter<SettingsUi, UserConnectedAdapter.ViewHolder>(UserConnectedDiff()) {
  val itemSelected: SingleLiveEvent<Item> by lazy {
    SingleLiveEvent<Item>()
  }

  init {
    submitList(items)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(ListItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    holder.apply {
      bind(item)
      itemView.setOnClickListener { itemSelected.setValue(Item.DISCONNECTION) }
      itemView.tag = item
    }
  }

  class ViewHolder(private val binding: ListItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: SettingsUi) {
      binding.settings = item
      binding.executePendingBindings()
    }
  }
}

class UserConnectedDiff : DiffUtil.ItemCallback<SettingsUi>() {
  override fun areItemsTheSame(oldItem: SettingsUi, newItem: SettingsUi): Boolean {
    return oldItem.title == newItem.title
  }

  override fun areContentsTheSame(oldItem: SettingsUi, newItem: SettingsUi): Boolean {
    return oldItem == newItem
  }
}

enum class Item {
  DISCONNECTION
}