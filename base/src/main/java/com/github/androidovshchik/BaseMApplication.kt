@file:Suppress("unused")

package com.github.androidovshchik

import android.content.Context
import android.support.multidex.MultiDex

@Suppress("MemberVisibilityCanBePrivate", "NON_EXHAUSTIVE_WHEN")
open class BaseMApplication : BaseApplication() {

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }
}