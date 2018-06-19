@file:Suppress("unused")

package com.github.androidovshchik.utils

import java.io.File
import java.io.FileOutputStream
import java.net.URL

object FileUtil {

    fun download(link: String, path: String) {
        val input = URL(link).openStream()
        val output = FileOutputStream(File(path))
        input.use { _ ->
            output.use { _ ->
                input.copyTo(output)
            }
        }
    }
}
