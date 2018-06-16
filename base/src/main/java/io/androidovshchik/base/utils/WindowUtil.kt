@file:Suppress("unused")

package io.androidovshchik.base.utils

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.view.Display
import android.os.Build
import timber.log.Timber

object WindowUtil {

    fun getWindowSize(context: Context): Point {
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val size = Point()
        manager.defaultDisplay.getSize(size)
        return size
    }

    fun getScreenSize(context: Context): Point {
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val size = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            manager.defaultDisplay.getRealSize(size)
        } else {
            try {
                val methodGetRawHeight = Display::class.java.getMethod("getRawHeight")
                val methodGetRawWidth = Display::class.java.getMethod("getRawWidth")
                size.y = methodGetRawHeight.invoke(manager.defaultDisplay) as Int
                size.x = methodGetRawWidth.invoke(manager.defaultDisplay) as Int
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        return size
    }

    fun showKeyboard(context: Context) {
        val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun hideKeyboard(context: Context) {
        val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }
}
