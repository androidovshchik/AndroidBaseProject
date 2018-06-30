package com.github.androidovshchik

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import android.support.annotation.DrawableRes
import com.github.androidovshchik.data.Preferences
import com.github.androidovshchik.receivers.ToastTrigger
import com.github.androidovshchik.utils.NotificationUtil
import io.reactivex.disposables.CompositeDisposable

@Suppress("MemberVisibilityCanBePrivate", "unused")
open class BaseService : Service() {

    private var wakeLock: PowerManager.WakeLock? = null

    protected var disposable = CompositeDisposable()

    protected lateinit var preferences: Preferences

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        preferences = Preferences(applicationContext)
    }

    @SuppressLint("WakelockTimeout")
    protected fun acquireWakeLock() {
        val manager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = manager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getString(R.string.library_name))
        wakeLock!!.acquire()
    }

    protected fun startForeground(id: Int, title: String, @DrawableRes icon: Int) {
        startForeground(id, NotificationUtil.makeSilent(applicationContext, title, icon))
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
