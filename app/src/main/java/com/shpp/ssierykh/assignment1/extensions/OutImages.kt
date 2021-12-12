package com.shpp.ssierykh.assignment1.extensions

import CircleTransform
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

object OutImages {

    fun AppCompatImageView.loadImageGlade(

        loadPicture: Any?,
    ) {
        Glide
            .with(this)
            .load(loadPicture)
            .circleCrop()
            .into(this)
    }

    fun AppCompatImageView.loadImagePicasso(
        loadPicture: String
    ) {
        Picasso.get()
            .load(loadPicture)
            .transform(CircleTransform())
            .into(this)
    }
}