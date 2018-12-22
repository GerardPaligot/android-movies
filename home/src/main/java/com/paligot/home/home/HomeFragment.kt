package com.paligot.home.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.paligot.home.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : Fragment() {
  override fun onAttach(context: Context?) {
    super.onAttach(context)
    Timber.plant(Timber.DebugTree())
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val binding = FragmentHomeBinding.inflate(inflater, container, false)
    binding.mediaRecyclerView.apply {
      setHasFixedSize(true)
      adapter = HomeAdapter()
    }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    (mediaRecyclerView.adapter as HomeAdapter).submitList(
      arrayListOf(
        MediaListUi(
          "Most Popular", arrayListOf(
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg")
          )
        ),
        MediaListUi(
          "Top Selling", arrayListOf(
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg")
          )
        ),
        MediaListUi(
          "Recommended for you", arrayListOf(
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg"),
            MediaUi("Aquaman", "https://image.tmdb.org/t/p/w154/fBfrd2GPTawZjxaBt63zDa2t5hL.jpg")
          )
        )
      )
    )
  }
}