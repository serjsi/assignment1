package com.shpp.ssierykh.assignment1.utils.extensions

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import com.shpp.ssierykh.assignment1.utils.CircleTransform
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.shpp.ssierykh.assignment1.R
import com.squareup.picasso.Picasso


fun AppCompatImageView.loadImageGlade(
    loadPicture: Int?,
    ) {
    Glide
        .with(this)
        .load(loadPicture)
        .circleCrop()
        .error(R.drawable.ic_mok)
        .into(this)
}

fun AppCompatImageView.loadImageGlade(
    loadPicture: String,
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
