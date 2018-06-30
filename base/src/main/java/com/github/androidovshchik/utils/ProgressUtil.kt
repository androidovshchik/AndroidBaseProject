@file:Suppress("unused", "DEPRECATION", "MemberVisibilityCanBePrivate")

package com.github.androidovshchik.utils

import android.app.Activity
import android.app.ProgressDialog
import com.github.androidovshchik.R

object ProgressUtil {

    private var progressDialog: ProgressDialog? = null

    fun showDialog(activity: Activity) {
        closeDialog()
        progressDialog = ProgressDialog.show(activity, activity.getString(R.string.wait),
            null, true)
    }

    fun closeDialog() {
        progressDialog?.dismiss()
        progressDialog = null
    }
}
