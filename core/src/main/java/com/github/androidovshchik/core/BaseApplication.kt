@file:Suppress("unused")

package com.github.androidovshchik.core

import android.app.Application
import android.support.annotation.StyleRes
import com.github.androidovshchik.core.utils.setupACRA
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate")
open class BaseApplication : Application() {

    protected open fun initTimber() {
        initTimber(Timber.DebugTree())
    }

    protected open fun initACRA() {
        initACRA(R.style.Library_Dialog, "vladkalyuzhnyu@gmail.com")
    }

    protected open fun initACRA(replyEmail: String) {
        initACRA(R.style.Library_Dialog, replyEmail)
    }

    protected fun initTimber(tree: Timber.Tree) {
        Timber.plant(tree)
    }

    protected fun initACRA(@StyleRes dialogTheme: Int, replyEmail: String) {
        setupACRA(dialogTheme, replyEmail)
    }
}