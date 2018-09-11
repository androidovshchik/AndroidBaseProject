package com.github.androidovshchik.core

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import com.github.androidovshchik.core.utils.context.newIntent
import com.github.androidovshchik.core.utils.context.restartForegroundService
import com.github.androidovshchik.core.utils.context.restartService
import com.github.androidovshchik.core.utils.context.stopService

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseBActivity<S : BaseBService> : BaseActivity() {

    abstract val serviceClass: Class<out BaseBService>?

    abstract val foreground: Boolean

    var service: S? = null

    override fun onStart() {
        super.onStart()
        if (serviceClass != null) {
            if (foreground) {
                restartForegroundService(serviceClass!!)
            } else {
                restartService(serviceClass!!)
            }
            bindService(newIntent(serviceClass!!), serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (service != null) {
            unbindService(serviceConnection)
            stopService(serviceClass!!)
        }
    }

    private val serviceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName) {
            service = null
        }

        @Suppress("UNCHECKED_CAST")
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            service = (binder as BaseBService.BaseBinder).service as S
        }
    }
}
