@file:Suppress("unused")

package com.github.androidovshchik.core

import android.app.Application
import com.github.androidovshchik.core.utils.context.isDebug
import com.github.androidovshchik.core.utils.initACRA
import org.acra.ACRA
import timber.log.Timber

open class BaseApplication : Application() {

    protected open var dialogTheme = R.style.Library_Dialog

    protected open var replyEmail = "vladkalyuzhnyu@gmail.com"

    override fun onCreate() {
        super.onCreate()
        if (ACRA.isACRASenderServiceProcess()) {
            return
        }
        if (isDebug()) {
            Timber.plant(Timber.DebugTree())
            initACRA(dialogTheme, replyEmail)
        }
    }
}