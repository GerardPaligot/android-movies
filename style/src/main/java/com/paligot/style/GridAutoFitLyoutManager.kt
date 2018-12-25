package com.paligot.style

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GridAutofitLayoutManager : GridLayoutManager {
  private var columnWidth: Float = 0F

  private var currentColumnWidth = -1F
  private var currentWidth = -1
  private var currentHeight = -1

  constructor(context: Context) : super(context, 1) {
    setColumnWidthByResource(context, -1)
  }

  constructor(context: Context, resource: Int) : this(context) {
    setColumnWidthByResource(context, resource)
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
      : super(context, attrs, defStyleAttr, defStyleRes) {
    setColumnWidthByResource(context, -1)
  }

  constructor(context: Context, resource: Int, orientation: Int, reverseLayout: Boolean)
      : super(context, 1, orientation, reverseLayout) {
    setColumnWidthByResource(context, resource)
  }

  private fun setColumnWidthByResource(context: Context, resource: Int) {
    if (resource >= 0) {
      columnWidth = context.resources.getDimension(resource)
    } else {
      columnWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120f, context.resources.displayMetrics)
    }
  }

  override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
    recalculateSpanCount()
    super.onLayoutChildren(recycler, state)
  }

  private fun recalculateSpanCount() {
    if (width <= 0 || height <= 0 || columnWidth <= 0) return
    if (width != currentWidth || height != currentHeight || columnWidth != currentColumnWidth) {
      val totalSpace: Int
      if (orientation == LinearLayoutManager.VERTICAL) {
        totalSpace = width - paddingRight - paddingLeft
      } else {
        totalSpace = height - paddingTop - paddingBottom
      }
      val spanCount = Math.max(1.0, Math.floor((totalSpace / columnWidth).toDouble())).toInt()
      setSpanCount(spanCount)
      currentColumnWidth = columnWidth
      currentWidth = width
      currentHeight = height
    }
  }
}