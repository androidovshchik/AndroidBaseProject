package com.github.androidovshchik

import android.content.Intent
import android.os.Binder
import android.os.IBinder

abstract class BaseFBService : BaseFService() {

    private val binder = BaseBinder()

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onUnbind(intent: Intent): Boolean {
        return true
    }

    @Suppress("unused")
    inner class BaseBinder : Binder() {

        internal val service: BaseFBService get() = this@BaseFBService
    }
}
