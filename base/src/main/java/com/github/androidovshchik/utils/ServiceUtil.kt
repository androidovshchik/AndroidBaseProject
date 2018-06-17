@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.github.androidovshchik.utils

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build

object ServiceUtil {

    @Suppress("DEPRECATION")
    fun isRunning(context: Context, serviceClass: Class<out Service>): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    fun forceStartService(context: Context, serviceClass: Class<out Service>) {
        stopService(context, serviceClass)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(Intent(context, serviceClass))
        } else {
            context.startService(Intent(context, serviceClass))
        }
    }

    fun stopService(context: Context, serviceClass: Class<out Service>) {
        if (isRunning(context, serviceClass)) {
            context.stopService(Intent(context, serviceClass))
        }
    }
}