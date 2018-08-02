@file:Suppress("unused")

package com.github.androidovshchik.utils

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import timber.log.Timber

@SuppressLint("MissingPermission")
fun Context.makeCall(number: String) {
    try {
        val intent = Intent(Intent.ACTION_CALL, number.phone2Uri())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    } catch (e: SecurityException) {
        Timber.e(e)
    }
}

fun Context.sendSMS(phone: String, text: String) {
    try {
        val intent = PendingIntent.getActivity(this, 0, Intent(), 0)
        SmsManager.getDefault()
            .sendTextMessage(phone, null, text, intent, null)
    } catch (e: SecurityException) {
        Timber.e(e)
    }
}