package com.github.androidovshchik.core

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import com.github.androidovshchik.core.receivers.ToastTrigger
import com.github.androidovshchik.core.utils.context.newIntent
import com.github.androidovshchik.core.utils.context.newWakeLock
import io.reactivex.disposables.CompositeDisposable

@Suppress("MemberVisibilityCanBePrivate", "unused")
open class BaseService : Service() {

    private var wakeLock: PowerManager.WakeLock? = null

    protected var disposable = CompositeDisposable()

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @SuppressLint("WakelockTimeout")
    protected fun acquireWakeLock() {
        wakeLock = newWakeLock(javaClass.simpleName)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return Service.START_NOT_STICKY
    }

    protected fun showToast(message: String) {
        sendBroadcast(newIntent(ToastTrigger::class.java).apply {
            putExtra(ToastTrigger.EXTRA_MESSAGE, message)
        })
    }

    protected fun stopWork() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
        wakeLock?.release()
        wakeLock = null
    }
}
