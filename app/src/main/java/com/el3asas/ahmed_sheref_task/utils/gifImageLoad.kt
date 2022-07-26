package com.el3asas.ahmed_sheref_task.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:loadGifImage", "app:placeHolder", requireAll = false)
fun loadGifImage(
    v: View,
    img: Drawable,
    drawable: Drawable? = null,
) {
    val imageView = v as ImageView

    Glide.with(v)
        .asGif()
        .load(img)
        .placeholder(drawable)
        .centerInside()
        .into(imageView)

}