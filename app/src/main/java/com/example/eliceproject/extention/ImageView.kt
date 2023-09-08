package com.example.eliceproject.extention

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.example.eliceproject.R
import com.example.eliceproject.util.PrintLog

@BindingAdapter(
    value = [
        "imageViewBind:centerCropImage",
        "imageViewBind:centerCropDefaultImage",
    ],
    requireAll = false,
)
fun <T> ImageView.setCenterCropImage(
    image: T?,
    defaultImage: Any?,
) {
    PrintLog.d("defaultImage", defaultImage)
    setGlideImage(
        image = image,
        defaultImage = defaultImage,
        glideOption = RequestOptions().centerCrop(),
    ).into(this)
}

private fun <T> View.setGlideImage(
    image: T?,
    defaultImage: Any? = null,
    glideOption: RequestOptions,
): RequestBuilder<Drawable> =
    Glide.with(this)
        .load(image)
        .let { // defaultImage 설정
            if (defaultImage == null) {
                it.fallback(null)
                    .placeholder(null)
            } else {
                when (defaultImage) {
                    is Int -> it.fallback(defaultImage).error(defaultImage)
                    is Drawable -> it.fallback(defaultImage).error(defaultImage)
                    else -> it.error(defaultImage)
                }
            }
        }
        .apply(glideOption)
