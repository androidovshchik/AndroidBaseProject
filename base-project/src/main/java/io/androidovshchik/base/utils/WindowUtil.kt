@file:Suppress("unused")

package io.androidovshchik.base.utils

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

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
        manager.defaultDisplay.getRealSize(size)
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
