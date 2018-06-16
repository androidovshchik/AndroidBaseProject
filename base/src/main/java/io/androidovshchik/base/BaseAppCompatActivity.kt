package io.androidovshchik.base

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

@Suppress("MemberVisibilityCanBePrivate")
open class BaseAppCompatActivity : AppCompatActivity() {

    protected val disposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
