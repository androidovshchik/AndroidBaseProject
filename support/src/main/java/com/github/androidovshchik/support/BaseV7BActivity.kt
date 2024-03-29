package com.github.androidovshchik.support

import android.content.res.Configuration
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatDelegate
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import com.github.androidovshchik.core.BaseBActivity
import com.github.androidovshchik.core.BaseBService

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseV7BActivity<S : BaseBService> : BaseBActivity<S>() {

    private var delegate: AppCompatDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        getDelegate().installViewFactory()
        getDelegate().onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        getDelegate().onPostCreate(savedInstanceState)
    }

    override fun onPostResume() {
        super.onPostResume()
        getDelegate().onPostResume()
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        getDelegate().setContentView(layoutResID)
    }

    override fun setContentView(view: View) {
        getDelegate().setContentView(view)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        getDelegate().setContentView(view, params)
    }

    override fun addContentView(view: View, params: ViewGroup.LayoutParams) {
        getDelegate().addContentView(view, params)
    }

    override fun onTitleChanged(title: CharSequence, color: Int) {
        super.onTitleChanged(title, color)
        getDelegate().setTitle(title)
    }

    @Suppress("unused")
    fun getSupportActionBar(): ActionBar? {
        return getDelegate().supportActionBar
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        getDelegate().onConfigurationChanged(newConfig)
    }

    override fun getMenuInflater(): MenuInflater {
        return getDelegate().menuInflater
    }

    override fun invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu()
    }

    override fun onStop() {
        super.onStop()
        getDelegate().onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        getDelegate().onDestroy()
    }

    private fun getDelegate(): AppCompatDelegate {
        if (delegate == null) {
            delegate = AppCompatDelegate.create(this, null)
        }
        return delegate!!
    }
}
