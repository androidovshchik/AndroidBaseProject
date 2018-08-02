@file:Suppress("DEPRECATION", "unused")

package com.github.androidovshchik.utils

import android.app.Activity
import android.app.ProgressDialog
import com.github.androidovshchik.R

fun ProgressDialog.show(activity: Activity): ProgressDialog {
    dismiss()
    return ProgressDialog.show(activity, activity.getString(R.string.wait), null, true)
}