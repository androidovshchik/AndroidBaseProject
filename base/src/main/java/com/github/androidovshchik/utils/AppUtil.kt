@file:Suppress("unused")

package com.github.androidovshchik.utils

import com.github.androidovshchik.BuildConfig

object AppUtil {

    fun isDebug(): Boolean {
        return BuildConfig.BUILD_TYPE == "debug"
    }

    fun isPresentation(): Boolean {
        return BuildConfig.BUILD_TYPE == "presentation"
    }

    fun isRelease(): Boolean {
        return BuildConfig.BUILD_TYPE == "release"
    }
}
