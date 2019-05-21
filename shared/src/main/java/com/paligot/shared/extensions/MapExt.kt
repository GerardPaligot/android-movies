package com.paligot.shared.extensions

fun <K, V> Map<K, V>.indexOf(key: K): Int {
  var index = 0
  this.forEach {
    if (it.key == key) return@indexOf index
    index++
  }
  return -1
}

fun <K, V> Map<K, V>.getOf(position: Int): V {
  var index = 0
  this.forEach {
    if (index == position) return@getOf it.value
    index++
  }
  throw ArrayIndexOutOfBoundsException(position)
}