package com.github.androidovshchik.core

import android.content.Intent
import android.os.Binder
import android.os.IBinder

abstract class BaseBService : BaseService() {

    private val binder = BaseBinder()

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onUnbind(intent: Intent): Boolean {
        return true
    }

    @Suppress("unused")
    inner class BaseBinder : Binder() {

        val service: BaseBService get() = this@BaseBService
    }
}
