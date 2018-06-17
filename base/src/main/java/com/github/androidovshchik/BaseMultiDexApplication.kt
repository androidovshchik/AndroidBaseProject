@file:Suppress("unused")

package com.github.androidovshchik

import android.support.multidex.MultiDexApplication
import android.text.TextUtils
import com.github.androidovshchik.data.Preferences
import com.github.androidovshchik.utils.ACRAUtil
import com.github.androidovshchik.utils.AppUtil
import com.github.androidovshchik.utils.StethoUtil
import org.acra.ACRA
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseMultiDexApplication: MultiDexApplication() {

    abstract val sandbox: Boolean

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