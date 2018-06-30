package com.github.androidovshchik

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.androidovshchik.data.Preferences
import io.reactivex.disposables.CompositeDisposable

@Suppress("MemberVisibilityCanBePrivate")
open class BaseV7Activity : AppCompatActivity() {

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
