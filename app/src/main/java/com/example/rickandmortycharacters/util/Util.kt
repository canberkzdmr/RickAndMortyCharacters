package com.example.rickandmortycharacters.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmortycharacters.R

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url, placeHolderProgressBar(view.context))
}

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {
    Log.d("ImageView", "downloadFromUrl: $url")

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.rick_not_found)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}