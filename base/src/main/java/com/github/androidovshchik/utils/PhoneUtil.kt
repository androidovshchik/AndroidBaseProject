@file:Suppress("unused")

package com.github.androidovshchik.utils

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import timber.log.Timber

object PhoneUtil {

    fun sendSMS(context: Context, phone: String, text: String) {
        if (!AppUtil.isDebug()) {
            try {
                val intent = PendingIntent.getActivity(context, 0, Intent(), 0)
                SmsManager.getDefault()
                    .sendTextMessage(phone, null, text, intent, null)
            } catch (e: SecurityException) {
                Timber.e(e)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun makeCall(context: Context, number: String) {
        try {
            val intent = Intent(Intent.ACTION_CALL, toUri(number))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun killCall(manager: TelephonyManager): Boolean {
        try {
            // Get the getITelephony() method
            val classTelephony = Class.forName(manager.javaClass.name)
            val methodGetITelephony = classTelephony.getDeclaredMethod("getITelephony")
            // Ignore that the method is supposed to be private
            methodGetITelephony.isAccessible = true
            // Invoke getITelephony() to get the ITelephony interface
            val telephonyInterface = methodGetITelephony.invoke(manager)
            // Get the endCall method from ITelephony
            val telephonyInterfaceClass = Class.forName(telephonyInterface.javaClass.name)
            val methodEndCall = telephonyInterfaceClass.getDeclaredMethod("endCall")
            // Invoke endCall()
            methodEndCall.invoke(telephonyInterface)
        } catch (e: Exception) {
            // Many things can go wrong with reflection calls
            Timber.e(e)
            return false
        }
        return true
    }

    private fun toUri(phone: String): Uri {
        var uri = ""
        if (!phone.startsWith("tel:")) {
            uri += "tel:"
        }
        for (c in phone.toCharArray()) {
            uri += when (c) {
                '#' -> Uri.encode("#")
                else -> c
            }
        }
        return Uri.parse(uri)
    }
}