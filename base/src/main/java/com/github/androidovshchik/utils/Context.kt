@file:Suppress("unused")

package com.github.androidovshchik.utils

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import timber.log.Timber

val Context.appContext: Context get() = applicationContext

val Context.activityManager: ActivityManager get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

val Context.inputMethodManager: InputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val Context.keyguardManager: KeyguardManager get() = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

val Context.notificationManager: NotificationManager get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

val Context.alarmManager: AlarmManager get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager

val Context.telephonyManager: TelephonyManager get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

val Context.windowManager: WindowManager get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

fun Context.isBuildConfigDebug(): Boolean {
    try {
        return Class.forName("$packageName.BuildConfig")
            .getField("DEBUG")
            .get(null) as Boolean
    } catch (e: Exception) {
        Timber.e(e)
    }
    return true
}

fun Context.newIntent(serviceClass: Class<out Any>): Intent {
    return Intent(appContext, serviceClass)
}

fun Context.startService(serviceClass: Class<out Service>) {
    startService(newIntent(serviceClass))
}

fun Context.startForegroundService(serviceClass: Class<out Service>) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(newIntent(serviceClass))
    } else {
        startService(serviceClass)
    }
}

fun Context.forceRestartService(serviceClass: Class<out Service>) {
    stopService(serviceClass)
    startService(serviceClass)
}

fun Context.forceRestartForegroundService(serviceClass: Class<out Service>) {
    stopService(serviceClass)
    startForegroundService(serviceClass)
}

fun Context.stopService(serviceClass: Class<out Service>) {
    if (activityManager.isServiceRunning(serviceClass)) {
        stopService(newIntent(serviceClass))
    }
}

@SuppressLint("NewApi")
fun Context.nextAlarm(interval: Int, receiverClass: Class<out BroadcastReceiver>) {
    cancelAlarm(receiverClass)
    val sdkInt = Build.VERSION.SDK_INT
    val intent = PendingIntent.getBroadcast(appContext, 0, newIntent(receiverClass), 0)
    when {
        sdkInt >= Build.VERSION_CODES.M -> alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, intent)
        sdkInt >= android.os.Build.VERSION_CODES.KITKAT -> alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, intent)
        else -> alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, intent)
    }
}

fun Context.cancelAlarm(receiverClass: Class<out BroadcastReceiver>) {
    alarmManager.cancel(PendingIntent.getBroadcast(appContext, 0, newIntent(receiverClass), 0))
}

@SuppressLint("MissingPermission")
fun Context.makeCall(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_CALL, phone.phone2Uri())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

fun Context.sendSMS(phone: String, text: String) {
    try {
        SmsManager.getDefault()
            .sendTextMessage(phone, null, text, PendingIntent.getActivity(appContext, 0,
                Intent(), 0), null)
    } catch (e: Exception) {
        Timber.e(e)
    }
}