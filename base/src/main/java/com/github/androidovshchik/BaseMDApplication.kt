@file:Suppress("unused")

package com.github.androidovshchik

import android.support.multidex.MultiDexApplication
import android.text.TextUtils
import com.facebook.stetho.Stetho
import com.github.androidovshchik.data.Preferences
import com.github.androidovshchik.utils.ACRAUtil
import com.github.androidovshchik.utils.appContext
import com.github.androidovshchik.utils.isBuildConfigDebug
import com.github.androidovshchik.utils.newLine
import org.acra.ACRA
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate", "NON_EXHAUSTIVE_WHEN")
abstract class BaseMDApplication: MultiDexApplication() {

    abstract val theme: Int

    protected lateinit var preferences: Preferences

    override fun onCreate() {
        super.onCreate()
        if (ACRA.isACRASenderServiceProcess()) {
            return
        }
        if (isBuildConfigDebug()) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(appContext)
            ACRAUtil.init(this, theme)
        }
        preferences = Preferences(applicationContext)
        Timber.d(TextUtils.join(newLine(), preferences.getAllSorted()))
    }
}