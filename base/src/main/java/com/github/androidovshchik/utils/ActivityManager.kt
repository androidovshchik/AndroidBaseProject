@file:Suppress("unused")

package com.github.androidovshchik.utils

import android.app.ActivityManager
import android.app.Service

@Suppress("DEPRECATION")
fun ActivityManager.isServiceRunning(serviceClass: Class<out Service>): Boolean {
    for (service in getRunningServices(Integer.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}