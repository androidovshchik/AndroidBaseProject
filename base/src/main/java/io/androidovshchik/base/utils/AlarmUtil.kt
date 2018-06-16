@file:Suppress("unused")

package io.androidovshchik.base.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock

object AlarmUtil {

    @SuppressLint("NewApi")
    fun next(context: Context, interval: Int, clss: Class<out BroadcastReceiver>) {
        cancel(context, clss)
        val sdkInt = Build.VERSION.SDK_INT
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(context, 0, Intent(context, clss), 0)
        when {
            sdkInt >= Build.VERSION_CODES.M -> manager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + interval, pendingIntent)
            sdkInt >= android.os.Build.VERSION_CODES.KITKAT -> manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + interval, pendingIntent)
            else -> manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + interval, pendingIntent)
        }
    }

    private fun cancel(context: Context, clss: Class<out BroadcastReceiver>) {
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(context, 0, Intent(context, clss), 0)
        manager.cancel(pendingIntent)
    }
}
