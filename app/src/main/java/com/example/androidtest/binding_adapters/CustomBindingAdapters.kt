package com.example.androidtest.binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


object CustomBindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(url: String?) {
        Picasso.get().load(url).into(this)
    }

}



