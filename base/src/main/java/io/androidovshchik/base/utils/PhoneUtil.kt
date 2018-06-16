@file:Suppress("unused")

package io.androidovshchik.base.utils

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import io.androidovshchik.base.BuildConfig

object PhoneUtil {

    fun sendSMS(context: Context, phone: String, text: String) {
        if (!BuildConfig.DEBUG) {
            val intent = PendingIntent.getActivity(context, 0, Intent(), 0)
            SmsManager.getDefault()
                .sendTextMessage(phone, null, text, intent, null)
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
            e.printStackTrace()
            return false
        }
        return true
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
