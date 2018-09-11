@file:Suppress("unused")

package com.github.androidovshchik.core.utils

import android.content.res.Resources
import java.io.File

val sep: String get() = File.separator

fun newLine(): String {
    return System.getProperty("line.separator")?: "\n"
}

fun dp2px(dp: Float): Int {
    return Math.round(dp * Resources.getSystem().displayMetrics.density)
}