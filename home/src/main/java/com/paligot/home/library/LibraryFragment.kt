package com.paligot.home.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.paligot.home.R
import com.paligot.home.databinding.FragmentLibraryBinding
import com.paligot.home.home.MediaListAdapter
import com.paligot.style.Direction
import com.paligot.style.MarginItemDecoration

class LibraryFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val binding = FragmentLibraryBinding.inflate(inflater, container, false)
    binding.mediaRecyclerView.apply {
      setHasFixedSize(true)
      adapter = MediaListAdapter()
      addItemDecoration(
        MarginItemDecoration(
          binding.root.resources.getDimensionPixelSize(R.dimen.list_media_spacing),
          Direction.FULL
        )
      )
    }
    return binding.root
  }
}