package com.github.androidovshchik

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseBActivity<S: BaseFBService> : BaseActivity() {

    abstract val serviceClass: Class<out BaseFBService>?

    var service: S? = null

    override fun onStart() {
        super.onStart()
        if (serviceClass != null) {
            val intent = Intent(applicationContext, serviceClass)
            startService(intent)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (service != null) {
            unbindService(serviceConnection)
        }
    }

    private val serviceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName) {
            service = null
        }

        @Suppress("UNCHECKED_CAST")
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            service = (binder as BaseFBService.BaseBinder).service as S
        }
    }
}
