@file:Suppress("unused")

package com.github.androidovshchik.core.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.net.URL

fun File.copyFromAssets(context: Context, name: String) {
    val input = context.assets.open(name)
    val output = FileOutputStream(this)
    input.use { _ ->
        output.use { _ ->
            input.copyTo(output)
        }
    }
}

fun File.copyFromRaw(context: Context, id: Int) {
    val input = context.resources.openRawResource(id)
    val output = FileOutputStream(this)
    input.use { _ ->
        output.use { _ ->
            input.copyTo(output)
        }
    }
}

fun File.download(link: String) {
    val input = URL(link).openStream()
    val output = FileOutputStream(this)
    input.use { _ ->
        output.use { _ ->
            input.copyTo(output)
        }
    }
}