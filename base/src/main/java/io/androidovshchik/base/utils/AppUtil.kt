@file:Suppress("unused")

package io.androidovshchik.base.utils

import io.androidovshchik.base.BuildConfig

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
