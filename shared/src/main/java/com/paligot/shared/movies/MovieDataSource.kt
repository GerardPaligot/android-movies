package com.paligot.shared.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.paligot.shared.extensions.plus
import com.paligot.shared.extensions.toString
import com.paligot.shared.session.SessionRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import java.util.*

const val POPULAR = 0
const val RECOMMENDATION = 1
const val TRENDING = 2
const val UP_COMING = 3
const val TOP_SELLING = 4

class MovieDataSource(
  private val tag: Int,
  private val service: MoviesService,
  private val session: SessionRepository
) : PageKeyedDataSource<Int, Movie>() {
  private val subscription = CompositeDisposable()
  private val fromDate = Calendar.getInstance().time.toString("yyyy-MM-dd")
  private val toDate = (Calendar.getInstance().time + 15).toString("yyyy-MM-dd")

  override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
    val list = when (tag) {
      POPULAR -> service.populars("fr", "FR", 1)
      TRENDING -> service.trending("fr", "FR", 1)
      UP_COMING -> service.upComing("fr", "FR", 1)
      TOP_SELLING -> service.topSelling("fr", "FR", fromDate, toDate, 1)
      RECOMMENDATION -> session.token.zipWith(
        session.accountId,
        BiFunction<String, String, Pair<String, String>> { id, token ->
          return@BiFunction Pair(id, token)
        }).flatMap { service.recommendations("Bearer ${it.first}", it.second, 1) }
      else -> throw NotImplementedError()
    }
    subscription.add(
      list
        .subscribe({
          callback.onResult(it.results, 0, it.count, null, 2)
        }, {
          // TODO
        })
    )
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    val list = when (tag) {
      POPULAR -> service.populars("fr", "FR", params.key)
      TRENDING -> service.trending("fr", "FR", params.key)
      UP_COMING -> service.upComing("fr", "FR", params.key)
      TOP_SELLING -> service.topSelling("fr", "FR", fromDate, toDate, params.key)
      RECOMMENDATION -> session.token.zipWith(
        session.accountId,
        BiFunction<String, String, Pair<String, String>> { id, token ->
          return@BiFunction Pair(id, token)
        }).flatMap { service.recommendations("Bearer ${it.first}", it.second, params.key) }
      else -> throw NotImplementedError()
    }
    subscription.add(
      list
        .subscribe({
          callback.onResult(it.results, params.key + 1)
        }, {
          // TODO
        })
    )
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    val previous = if (params.key > 1) params.key - 1 else null
    val list = when (tag) {
      POPULAR -> service.populars("fr", "FR", params.key)
      TRENDING -> service.trending("fr", "FR", params.key)
      UP_COMING -> service.populars("fr", "FR", params.key)
      TOP_SELLING -> service.topSelling("fr", "FR", fromDate, toDate, params.key)
      RECOMMENDATION -> session.token.zipWith(
        session.accountId,
        BiFunction<String, String, Pair<String, String>> { id, token ->
          return@BiFunction Pair(id, token)
        }).flatMap { service.recommendations("Bearer ${it.first}", it.second, params.key) }
      else -> throw NotImplementedError()
    }
    subscription.add(
      list
        .subscribe({
          callback.onResult(it.results, previous)
        }, {
          // TODO
        })
    )
  }

  class Factory(private val tag: Int, private val service: MoviesService, private val session: SessionRepository) :
    DataSource.Factory<Int, Movie>() {
    val source: MutableLiveData<MovieDataSource> by lazy {
      MutableLiveData<MovieDataSource>()
    }

    override fun create(): DataSource<Int, Movie> {
      val movieDataSource = MovieDataSource(tag, service, session)
      source.postValue(movieDataSource)
      return movieDataSource
    }
  }
}