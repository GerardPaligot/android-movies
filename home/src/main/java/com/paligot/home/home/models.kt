package com.paligot.home.home

import androidx.paging.PagedList
import com.paligot.shared.movies.Movie

data class MediaListUi(val title: String, val medias: PagedList<Movie>)
