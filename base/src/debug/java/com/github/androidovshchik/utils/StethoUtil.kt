package com.github.androidovshchik.utils

import android.content.Context
import com.facebook.stetho.Stetho

object StethoUtil {

    fun init(context: Context) {
        Stetho.initializeWithDefaults(context)
    }
}