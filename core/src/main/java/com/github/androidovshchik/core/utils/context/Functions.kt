@file:Suppress("unused")

package com.github.androidovshchik.core.utils.context

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.telephony.SmsManager
import com.github.androidovshchik.core.utils.phone2Uri
import com.gun0912.tedpermission.TedPermission
import timber.log.Timber

fun Context.newIntent(anyClass: Class<out Any>): Intent {
    return Intent(applicationContext, anyClass)
}

fun Context.newPendingActivity(activityClass: Class<out Activity>): PendingIntent {
    return PendingIntent.getActivity(applicationContext, 0, newIntent(activityClass),
        PendingIntent.FLAG_UPDATE_CURRENT)
}

fun Context.newPendingReceiver(receiverClass: Class<out BroadcastReceiver>): PendingIntent {
    return PendingIntent.getBroadcast(applicationContext, 0, newIntent(receiverClass),
        PendingIntent.FLAG_UPDATE_CURRENT)
}

fun Context.startForegroundService(serviceClass: Class<out Service>) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(newIntent(serviceClass))
    } else {
        startService(newIntent(serviceClass))
    }
}

fun Context.restartService(serviceClass: Class<out Service>) {
    stopService(serviceClass)
    startService(newIntent(serviceClass))
}

fun Context.restartForegroundService(serviceClass: Class<out Service>) {
    stopService(serviceClass)
    startForegroundService(serviceClass)
}

fun Context.stopService(serviceClass: Class<out Service>) {
    if (isServiceRunning(serviceClass)) {
        stopService(newIntent(serviceClass))
    }
}

fun Context.isDenied(permission: String): Boolean {
    return TedPermission.isDenied(applicationContext, permission)
}

fun Context.isGranted(permission: String): Boolean {
    return areGranted(permission)
}

fun Context.areGranted(vararg permissions: String): Boolean {
    return TedPermission.isGranted(applicationContext, *permissions)
}

@SuppressLint("NewApi")
fun Context.nextAlarm(interval: Int, receiverClass: Class<out BroadcastReceiver>) {
    cancelAlarm(receiverClass)
    val sdkInt = Build.VERSION.SDK_INT
    when {
        sdkInt >= Build.VERSION_CODES.M -> alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, newPendingReceiver(receiverClass))
        sdkInt >= android.os.Build.VERSION_CODES.KITKAT -> alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, newPendingReceiver(receiverClass))
        else -> alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, newPendingReceiver(receiverClass))
    }
}

fun Context.isDebug(): Boolean {
    try {
        return Class.forName("$packageName.BuildConfig")
            .getField("DEBUG")
            .get(null) as Boolean
    } catch (e: Exception) {
        Timber.e(e)
    }
    return true
}

@SuppressLint("MissingPermission")
fun Context.makeCall(number: String) {
    try {
        val intent = Intent(Intent.ACTION_CALL, number.phone2Uri())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

fun Context.sendSMS(phone: String, text: String) {
    try {
        SmsManager.getDefault()
            .sendTextMessage(phone, null, text, PendingIntent.getActivity(applicationContext,
                0, Intent(), 0), null)
    } catch (e: Exception) {
        Timber.e(e)
    }
}