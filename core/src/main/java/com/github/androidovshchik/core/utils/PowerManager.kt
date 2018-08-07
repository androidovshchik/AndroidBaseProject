@file:Suppress("unused")

package com.github.androidovshchik.core.utils

import android.annotation.SuppressLint
import android.os.PowerManager

@SuppressLint("WakelockTimeout")
fun PowerManager.newWakeLock(name: String): PowerManager.WakeLock {
    val wakeLock = newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, name)
    wakeLock.acquire()
    return wakeLock
}