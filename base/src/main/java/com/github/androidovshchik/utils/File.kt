@file:Suppress("unused")

package com.github.androidovshchik.utils

import java.io.File
import java.io.FileOutputStream
import java.net.URL

fun File.download(link: String) {
    val input = URL(link).openStream()
    val output = FileOutputStream(this)
    input.use { _ ->
        output.use { _ ->
            input.copyTo(output)
        }
    }
}