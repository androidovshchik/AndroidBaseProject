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

fun TelephonyManager.killCall(): Boolean {
    try {
        // Get the getITelephony() method
        val methodGetITelephony = javaClass.getDeclaredMethod("getITelephony")
        // Ignore that the method is supposed to be private
        methodGetITelephony.isAccessible = true
        // Invoke getITelephony() to get the ITelephony interface
        val telephonyInterface = methodGetITelephony.invoke(this)
        // Get the endCall method from ITelephony
        val methodEndCall = telephonyInterface.javaClass
            .getDeclaredMethod("endCall")
        // Invoke endCall()
        methodEndCall.invoke(telephonyInterface)
    } catch (e: Exception) {
        // Many things can go wrong with reflection calls
        Timber.e(e)
        return false
    }
    return true
}