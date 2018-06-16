package io.androidovshchik.baseproject

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import android.support.annotation.DrawableRes
import io.androidovshchik.baseproject.data.Preferences
import io.androidovshchik.baseproject.receivers.ToastTrigger
import io.reactivex.disposables.CompositeDisposable

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseForegroundService : Service() {

    private var wakeLock: PowerManager.WakeLock? = null

    protected var disposable = CompositeDisposable()

    protected lateinit var preferences: Preferences

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @SuppressLint("WakelockTimeout")
    override fun onCreate() {
        super.onCreate()
        val manager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = manager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getString(R.string.library_name))
        wakeLock!!.acquire()
        preferences = Preferences(applicationContext)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return Service.START_NOT_STICKY
    }

    protected fun startForeground(id: Int, title: String, @DrawableRes image: Int) {
        startForeground(id, builder.build())
    }

    protected fun stopWork() {
        stopForeground(true)
        stopSelf()
    }

    fun showToast(message: String) {
        val intent = Intent()
        intent.action = "$packageName.TOAST"
        intent.putExtra(ToastTrigger.EXTRA_MESSAGE, message)
        sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
        wakeLock!!.release()
    }
}
