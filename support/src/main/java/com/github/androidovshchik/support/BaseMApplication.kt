@file:Suppress("unused")

package com.github.androidovshchik.support

import android.content.Context
import android.support.multidex.MultiDex
import com.github.androidovshchik.core.BaseApplication

open class BaseMApplication : BaseApplication() {

    override var dialogTheme = R.style.Library_Support_Dialog

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }
}