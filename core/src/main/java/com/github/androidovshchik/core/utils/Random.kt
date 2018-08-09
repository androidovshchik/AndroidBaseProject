@file:Suppress("unused")

package com.github.androidovshchik.core.utils

import java.util.*

fun Random.nextInt(range: IntRange): Int {
    return range.start + nextInt(range.last - range.start + 1)
}

fun Random.nextString(range: IntRange, chars: String): String {
    var output = ""
    val size = nextInt(range)
    for (i in 0 until size) {
        output += chars[Math.floor(Math.random() * chars.length).toInt()]
    }
    return output
}