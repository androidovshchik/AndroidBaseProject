@file:Suppress("unused")

package com.github.androidovshchik.utils

import android.content.res.Resources

fun dp2px(dp: Float): Int {
    return Math.round(dp * Resources.getSystem().displayMetrics.density)
}