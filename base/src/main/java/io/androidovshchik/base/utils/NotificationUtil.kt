@file:Suppress("unused")

package io.androidovshchik.base.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.annotation.DrawableRes
import io.androidovshchik.base.R

object NotificationUtil {

    private const val NOISY_CHANNEL_ID = "noisy_channel"
    private const val QUITE_CHANNEL_ID = "quite_channel"

    fun makeSilent(context: Context, title: String, @DrawableRes icon: Int): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(NotificationChannel(QUITE_CHANNEL_ID,
                context.getString(R.string.library_name), NotificationManager.IMPORTANCE_LOW))
        }
        return NotificationCompat.Builder(context, QUITE_CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(icon)
            .setSound(null)
            .build()
    }
}