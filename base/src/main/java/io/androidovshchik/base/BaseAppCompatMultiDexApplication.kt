@file:Suppress("unused")

package io.androidovshchik.base

import android.support.multidex.MultiDexApplication
import android.text.TextUtils
import io.androidovshchik.base.data.Preferences
import io.androidovshchik.base.utils.ACRAUtil
import io.androidovshchik.base.utils.AppUtil
import io.androidovshchik.base.utils.StethoUtil
import org.acra.ACRA
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate")
open class BaseAppCompatMultiDexApplication: MultiDexApplication() {

    protected lateinit var preferences: Preferences

    override fun onCreate() {
        super.onCreate()
        if (ACRA.isACRASenderServiceProcess()) {
            return
        }
        if (AppUtil.isDebug()) {
            StethoUtil.init(applicationContext)
        } else {
            ACRAUtil.init(this, R.style.LibraryTheme_Dialog)
        }
        if (!AppUtil.isRelease()) {
            Timber.plant(Timber.DebugTree())
        }
        preferences = Preferences(applicationContext)
        Timber.d(TextUtils.join(System.getProperty("line.separator"), preferences.getAllSorted()))
    }
}