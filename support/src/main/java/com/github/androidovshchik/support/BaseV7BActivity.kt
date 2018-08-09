package com.github.androidovshchik.support

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import com.github.androidovshchik.core.BaseBService
import com.github.androidovshchik.core.utils.context.*

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseV7BActivity<S : BaseBService> : BaseV7Activity() {

    abstract val serviceClass: Class<out BaseBService>?

    abstract val foreground: Boolean

    var service: S? = null

    override fun onStart() {
        super.onStart()
        if (serviceClass != null) {
            if (foreground) {
                forceRestartForegroundService(serviceClass!!)
            } else {
                forceRestartService(serviceClass!!)
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
