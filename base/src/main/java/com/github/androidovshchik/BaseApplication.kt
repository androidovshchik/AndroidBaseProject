@file:Suppress("unused")

package com.github.androidovshchik

import android.app.Application
import android.text.TextUtils
import com.github.androidovshchik.data.Preferences
import com.github.androidovshchik.utils.ACRAUtil
import com.github.androidovshchik.utils.StethoUtil
import org.acra.ACRA
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate", "NON_EXHAUSTIVE_WHEN")
abstract class BaseApplication: Application() {

    abstract val environment: Environment

    abstract val theme: Int

    protected lateinit var preferences: Preferences

    override fun onCreate() {
        super.onCreate()
        if (ACRA.isACRASenderServiceProcess()) {
            return
        }
        when {
            environment != Environment.PRODUCTION -> {
                Timber.plant(Timber.DebugTree())
            }
        }
        when (environment) {
            Environment.DEVELOP -> {
                StethoUtil.init(applicationContext)
            }
        }
        when (environment) {
            Environment.SANDBOX -> {
                ACRAUtil.init(this, theme)
            }
        }
        preferences = Preferences(applicationContext)
        Timber.d(TextUtils.join(System.getProperty("line.separator"), preferences.getAllSorted()))
    }
}