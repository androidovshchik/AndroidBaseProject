@file:Suppress("unused")

package com.github.androidovshchik.core.utils.context

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Point
import android.os.PowerManager
import android.preference.PreferenceManager
import android.telephony.TelephonyManager
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.github.androidovshchik.core.utils.*

val Context.sharedPreferences: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(applicationContext)

val Context.activityManager: ActivityManager get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

val Context.inputMethodManager: InputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val Context.keyguardManager: KeyguardManager get() = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

val Context.notificationManager: NotificationManager get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

val Context.alarmManager: AlarmManager get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager

val Context.telephonyManager: TelephonyManager get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

val Context.windowManager: WindowManager get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

val Context.powerManager: PowerManager get() = getSystemService(Context.POWER_SERVICE) as PowerManager

val Context.allAppPermissions: Array<String> get() = packageManager.getPackageInfo(packageName,
    PackageManager.GET_PERMISSIONS).requestedPermissions ?: arrayOf()

fun Context.isServiceRunning(serviceClass: Class<out Service>): Boolean {
    return activityManager.isServiceRunning(serviceClass)
}

fun Context.newWakeLock(name: String): PowerManager.WakeLock {
    return powerManager.newWakeLock(name)
}

fun Context.cancelAlarm(receiverClass: Class<out BroadcastReceiver>) {
    alarmManager.cancel(newPendingReceiver(receiverClass))
}

fun Context.createSilentChannel() {
    notificationManager.createSilentChannel()
}

fun Context.createNoisyChannel() {
    notificationManager.createNoisyChannel()
}

fun Context.showKeyboard() {
    inputMethodManager.showKeyboard()
}

fun Context.hideKeyboard() {
    inputMethodManager.hideKeyboard()
}

fun Context.getIMEI(): String? {
    return telephonyManager.readIMEI()
}

fun Context.getWindowSize(): Point {
    return windowManager.getWindowSize()
}

fun Context.getScreenSize(): Point {
    return windowManager.getScreenSize()
}

fun Context.killCall() {
    telephonyManager.killCall()
}