package com.github.androidovshchik

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.github.androidovshchik.utils.ServiceUtil

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseBActivity<S: BaseBService> : BaseActivity() {

    abstract val serviceClass: Class<out BaseBService>?

    abstract val foreground: Boolean

    var service: S? = null

    override fun onStart() {
        super.onStart()
        if (serviceClass != null) {
            ServiceUtil.stopService(applicationContext, serviceClass!!)
            val intent = Intent(applicationContext, serviceClass!!)
            ServiceUtil.startServiceRightWay(applicationContext, intent, foreground)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (service != null) {
            unbindService(serviceConnection)
            ServiceUtil.stopService(applicationContext, serviceClass!!)
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
