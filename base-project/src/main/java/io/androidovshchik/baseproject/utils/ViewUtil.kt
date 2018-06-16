@file:Suppress("unused")

package io.androidovshchik.baseproject.utils

import android.content.res.Resources

object ViewUtil {

    fun dp2px(dp: Float): Int {
        return Math.round(dp * Resources.getSystem().displayMetrics.density)
    }
}
