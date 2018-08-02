@file:Suppress("unused")

package com.github.androidovshchik.utils

import android.graphics.Point
import android.os.Build
import android.view.Display
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
            val methodGetRawHeight = Display::class.java.getMethod("getRawHeight")
            val methodGetRawWidth = Display::class.java.getMethod("getRawWidth")
            size.y = methodGetRawHeight.invoke(defaultDisplay) as Int
            size.x = methodGetRawWidth.invoke(defaultDisplay) as Int
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
    return size
}