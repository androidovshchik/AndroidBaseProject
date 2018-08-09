package com.github.androidovshchik.support

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

@Suppress("MemberVisibilityCanBePrivate")
open class BaseV7Activity : AppCompatActivity() {

    protected val disposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}