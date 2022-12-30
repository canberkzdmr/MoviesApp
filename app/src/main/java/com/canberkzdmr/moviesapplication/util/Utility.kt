package com.canberkzdmr.moviesapplication.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.canberkzdmr.moviesapplication.R
import com.canberkzdmr.moviesapplication.util.Constants.IMAGE_URL
import java.text.SimpleDateFormat

/**
 * Created on 12/25/2022.
 */
fun ImageView.downloadFromUrl(resource: String?, progressDrawable: CircularProgressDrawable) {
    val url = IMAGE_URL + resource
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .centerCrop()
        .error(R.mipmap.ic_launcher_round)

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

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url, placeHolderProgressBar(view.context))
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("android:formatDate")
fun TextView.formatDate(date: String?) {
    val parser = SimpleDateFormat("yyyy-MM-dd")
    val formatter = SimpleDateFormat("dd.MM.yyyy")
    this.text = if (date != null && date != "") {
        parser.parse(date)?.let { formatter.format(it) }
    } else {
        ""
    }

}

@SuppressLint("SimpleDateFormat")
fun getYear(date: String?): String {
    val parser = SimpleDateFormat("yyyy-MM-dd")
    val formatter = SimpleDateFormat("yyyy")
    return if(date != null && date != "") {
        " (" + parser.parse(date)?.let { formatter.format(it) }  + ")"
    } else {
        ""
    }
}
