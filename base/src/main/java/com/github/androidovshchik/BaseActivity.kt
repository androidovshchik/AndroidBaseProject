package com.github.androidovshchik

import android.app.Activity
import android.os.Bundle
import com.github.androidovshchik.data.Preferences
import io.reactivex.disposables.CompositeDisposable

@Suppress("MemberVisibilityCanBePrivate")
open class BaseActivity : Activity() {

    protected val disposable = CompositeDisposable()

    protected lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = Preferences(applicationContext)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
