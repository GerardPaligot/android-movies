package com.paligot.shared.bindings

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.loadAvatar(url: String?) {
  url?.let {
    Picasso.get().load(url).into(this)
  }
}

@BindingAdapter("imageRes")
fun ImageView.loadIcon(@DrawableRes icon: Int) {
  setImageResource(icon)
}