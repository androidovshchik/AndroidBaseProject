@file:Suppress("unused")

package com.github.androidovshchik.sqlite

import com.facebook.stetho.Stetho
import com.github.androidovshchik.core.BaseApplication
import com.github.androidovshchik.core.utils.context.appContext

open class BaseSApplication : BaseApplication() {

    override var dialogTheme = R.style.Library_Support_Dialog

    override fun initDebug() {
        super.initDebug()
        Stetho.initializeWithDefaults(appContext)
    }
}