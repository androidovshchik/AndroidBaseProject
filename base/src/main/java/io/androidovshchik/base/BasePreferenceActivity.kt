package io.androidovshchik.base

import android.os.Bundle
import android.preference.PreferenceActivity
import io.androidovshchik.base.data.Preferences
import io.reactivex.disposables.CompositeDisposable

@Suppress("MemberVisibilityCanBePrivate")
abstract class BasePreferenceActivity : PreferenceActivity() {

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
