package com.github.androidovshchik.support

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.reactivex.disposables.CompositeDisposable

@Suppress("MemberVisibilityCanBePrivate")
open class BaseV7Activity : AppCompatActivity() {

    protected val disposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}