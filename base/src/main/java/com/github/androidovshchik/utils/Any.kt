@file:Suppress("unused")

package com.github.androidovshchik.utils

import android.content.res.Resources

fun newLine(): String {
    return System.getProperty("line.separator")
}

fun dp2px(dp: Float): Int {
    return Math.round(dp * Resources.getSystem().displayMetrics.density)
}