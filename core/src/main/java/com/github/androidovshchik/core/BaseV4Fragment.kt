@file:Suppress("DEPRECATION", "MemberVisibilityCanBePrivate")

package com.github.androidovshchik.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable

@Suppress("unused")
abstract class BaseV4Fragment : Fragment() {

    abstract val layout: Int

    protected val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}