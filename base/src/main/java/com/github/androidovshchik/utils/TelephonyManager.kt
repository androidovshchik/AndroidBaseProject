@file:Suppress("unused")

package com.github.androidovshchik.utils

import android.annotation.SuppressLint
import android.telephony.TelephonyManager
import timber.log.Timber

@Suppress("DEPRECATION")
@SuppressLint("MissingPermission", "HardwareIds")
fun TelephonyManager.imei(): String? {
    try {
        return deviceId
    } catch (e: SecurityException) {
        Timber.e(e)
    }
    return null
}

fun TelephonyManager.killCall() {
    try {
        val methodGetITelephony = javaClass.getDeclaredMethod("getITelephony")
        methodGetITelephony.isAccessible = true
        val telephonyInterface = methodGetITelephony.invoke(this)
        telephonyInterface.javaClass
            .getDeclaredMethod("endCall")
            .invoke(telephonyInterface)
    } catch (e: Exception) {
        Timber.e(e)
    }
}