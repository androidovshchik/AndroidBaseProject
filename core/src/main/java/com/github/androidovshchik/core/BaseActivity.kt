package com.github.androidovshchik.core

import android.app.Activity
import io.reactivex.disposables.CompositeDisposable

@Suppress("MemberVisibilityCanBePrivate")
open class BaseActivity : Activity() {

    protected val disposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}