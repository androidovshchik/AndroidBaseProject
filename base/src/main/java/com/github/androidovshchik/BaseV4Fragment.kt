@file:Suppress("DEPRECATION", "MemberVisibilityCanBePrivate")

package com.github.androidovshchik

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.androidovshchik.data.Preferences
import io.reactivex.disposables.CompositeDisposable

@Suppress("unused")
abstract class BaseV4Fragment : Fragment() {

    abstract val layout: Int

    protected val disposable = CompositeDisposable()

    protected lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = Preferences(activity!!.applicationContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}