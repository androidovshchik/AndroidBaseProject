@file:Suppress("unused")

package com.github.androidovshchik.core.utils

import android.net.Uri

fun String.phone2Uri(): Uri {
    var uri = ""
    if (!startsWith("tel:")) {
        uri += "tel:"
    }
    val chars = toCharArray()
    for (c in chars) {
        uri += when (c) {
            '#' -> Uri.encode("#")
            else -> c
        }
    }
    return Uri.parse(uri)
}