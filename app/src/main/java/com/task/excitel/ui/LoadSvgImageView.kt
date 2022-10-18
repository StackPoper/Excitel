package com.task.excitel.ui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.task.excitel.R

class LoadSvgImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    fun loadSvgOrOther(myUrl: String?, cache: Boolean = true) {
        myUrl?.let {
            if (it.lowercase().endsWith("svg")) {
                val imageLoader = ImageLoader.Builder(context)
                    .componentRegistry {
                        add(SvgDecoder(context))
                    }.build()

                val request = ImageRequest.Builder(context).apply {
                    error(R.drawable.ic_broken_image)
                    placeholder(R.drawable.loading_animation)
                    data(it).decoder(SvgDecoder(context))
                }.target(this).build()

                imageLoader.enqueue(request)
            } else {
                val imageLoader = ImageLoader(context)

                val request = ImageRequest.Builder(context).apply {
                    if (cache) {
                        memoryCachePolicy(CachePolicy.ENABLED)
                    } else {
                        memoryCachePolicy(CachePolicy.DISABLED)
                    }
                    error(R.drawable.ic_broken_image)
                    placeholder(R.drawable.loading_animation)
                    data("$it")
                }.target(this).build()
                imageLoader.enqueue(request)
            }
        }
    }
}