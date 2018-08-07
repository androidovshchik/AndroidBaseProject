@file:Suppress("unused")

package com.github.androidovshchik.core.utils

import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import timber.log.Timber

fun WindowManager.getWindowSize(): Point {
    val size = Point()
    defaultDisplay.getSize(size)
    return size
}

fun WindowManager.getScreenSize(): Point {
    val size = Point()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        defaultDisplay.getRealSize(size)
    } else {
        try {
            val methodGetRawHeight = defaultDisplay.javaClass.getMethod("getRawHeight")
            val methodGetRawWidth = defaultDisplay.javaClass.getMethod("getRawWidth")
            size.y = methodGetRawHeight.invoke(defaultDisplay) as Int
            size.x = methodGetRawWidth.invoke(defaultDisplay) as Int
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
    return size
}