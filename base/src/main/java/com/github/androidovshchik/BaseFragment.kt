@file:Suppress("DEPRECATION", "MemberVisibilityCanBePrivate")

package com.github.androidovshchik

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.androidovshchik.data.Preferences
import io.reactivex.disposables.CompositeDisposable

@Suppress("unused")
abstract class BaseFragment : Fragment() {

    abstract val layout: Int

    protected val disposable = CompositeDisposable()

    protected lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = Preferences(activity.applicationContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return bindView(inflater.inflate(layout, container, false))
    }

    abstract fun bindView(view: View?): View

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}