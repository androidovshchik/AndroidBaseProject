@file:Suppress("unused")

package com.github.androidovshchik.core

import android.app.Application
import android.text.TextUtils
import com.github.androidovshchik.core.utils.context.isDebug
import com.github.androidovshchik.core.utils.context.preferences
import com.github.androidovshchik.core.utils.getAllSorted
import com.github.androidovshchik.core.utils.initACRA
import com.github.androidovshchik.core.utils.newLine
import org.acra.ACRA
import timber.log.Timber

open class BaseApplication : Application() {

    protected open var dialogTheme = R.style.Library_Dialog

    override fun onCreate() {
        super.onCreate()
        if (ACRA.isACRASenderServiceProcess()) {
            return
        }
        if (isDebug()) {
            Timber.plant(Timber.DebugTree())
            initACRA(dialogTheme)
        }
        Timber.d(TextUtils.join(newLine(), preferences.getAllSorted()))
    }
}