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
import android.view.inputmethod.InputMethodManager
import timber.log.Timber

val Context.appContext: Context get() = applicationContext

val Context.activityManager: ActivityManager get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

val Context.inputMethodManager: InputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val Context.keyguardManager: KeyguardManager get() = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

val Context.notificationManager: NotificationManager get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

val Context.alarmManager: AlarmManager get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager

val Context.telephonyManager: TelephonyManager get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

fun Context.stopService(serviceClass: Class<out Service>) {
    if (activityManager.isServiceRunning(serviceClass)) {
        stopService(Intent(appContext, serviceClass))
    }
}

fun Context.forceRestartService(serviceClass: Class<out Service>, isForeground: Boolean) {
    stopService(serviceClass)
    startServiceRightWay(serviceClass, isForeground)
}

fun Context.startServiceRightWay(serviceClass: Class<out Service>, isForeground: Boolean) {
    if (isForeground && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(Intent(appContext, serviceClass))
    } else {
        startService(Intent(appContext, serviceClass))
    }
}

@SuppressLint("NewApi")
fun Context.nextAlarm(interval: Int, receiverClass: Class<out BroadcastReceiver>) {
    cancelAlarm(receiverClass)
    val sdkInt = Build.VERSION.SDK_INT
    val intent = PendingIntent.getBroadcast(appContext, 0, Intent(appContext, receiverClass), 0)
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
    alarmManager.cancel(PendingIntent.getBroadcast(appContext, 0, Intent(appContext, receiverClass), 0))
}

@SuppressLint("MissingPermission")
fun Context.makeCall(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_CALL, phone.phone2Uri())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    } catch (e: SecurityException) {
        Timber.e(e)
    }
}

fun Context.sendSMS(phone: String, text: String) {
    try {
        SmsManager.getDefault()
            .sendTextMessage(phone, null, text, PendingIntent.getActivity(appContext, 0,
                Intent(), 0), null)
    } catch (e: SecurityException) {
        Timber.e(e)
    }
}