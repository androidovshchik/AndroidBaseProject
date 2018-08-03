package com.github.androidovshchik

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import com.github.androidovshchik.receivers.ToastTrigger
import com.github.androidovshchik.utils.newWakeLock
import com.github.androidovshchik.utils.powerManager
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
        wakeLock = powerManager.newWakeLock(getString(R.string.library_name))
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return Service.START_NOT_STICKY
    }

    protected fun showToast(message: String) {
        val intent = Intent(applicationContext, ToastTrigger::class.java)
        intent.putExtra(ToastTrigger.EXTRA_MESSAGE, message)
        sendBroadcast(intent)
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
