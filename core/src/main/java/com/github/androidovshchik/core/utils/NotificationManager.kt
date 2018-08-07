@file:Suppress("unused")

package com.github.androidovshchik.core.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import com.github.androidovshchik.core.NOISY_CHANNEL_ID
import com.github.androidovshchik.core.QUITE_CHANNEL_ID

fun NotificationManager.createSilentChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(QUITE_CHANNEL_ID, QUITE_CHANNEL_ID, NotificationManager.IMPORTANCE_LOW)
        channel.lockscreenVisibility = Notification.VISIBILITY_SECRET
        createNotificationChannel(channel)
    }
}

fun NotificationManager.createNoisyChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(NOISY_CHANNEL_ID, NOISY_CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH)
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = Color.BLUE
        channel.vibrationPattern = longArrayOf(1000, 1000)
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        createNotificationChannel(channel)
    }
}