package io.androidovshchik.base

import android.app.Application
import io.androidovshchik.base.data.Preferences
import io.androidovshchik.base.utils.ACRAUtil
import org.acra.ACRA

@Suppress("MemberVisibilityCanBePrivate")
open class BaseApplication: Application() {

    protected lateinit var preferences: Preferences

    override fun onCreate() {
        super.onCreate()
        if (ACRA.isACRASenderServiceProcess()) {
            return
        }
        ACRAUtil.init(this, R.style.LibraryTheme_Dialog)
        preferences = Preferences(applicationContext)
    }
}