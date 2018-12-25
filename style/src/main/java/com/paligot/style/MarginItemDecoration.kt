package com.paligot.style

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
  private val space: Int,
  private val direction: Direction
) : RecyclerView.ItemDecoration() {

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    with(outRect) {

      if (direction == Direction.HORIZONTAL) {
        left = if (parent.getChildAdapterPosition(view) == 0) space else 0
        right = space
      } else if (direction == Direction.VERTICAL) {
        top = if (parent.getChildAdapterPosition(view) == 0) space else 0
        bottom = space
      } else if (direction == Direction.FULL) {
        if (parent.layoutManager is GridLayoutManager) {
          if (parent.getChildAdapterPosition(view) < (parent.layoutManager as GridLayoutManager).spanCount) top = space
          else top = 0
        } else {
          top = if (parent.getChildAdapterPosition(view) == 0) space else 0
        }
        left = space
        right = space
        bottom = space
      } else {
        TODO(reason = "Please implement the new direction")
      }
    }
  }
}

enum class Direction {
  HORIZONTAL, VERTICAL, FULL
}