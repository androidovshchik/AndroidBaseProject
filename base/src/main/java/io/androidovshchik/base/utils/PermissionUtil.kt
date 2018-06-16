@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package io.androidovshchik.base.utils

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.os.Build
import android.app.Activity
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import io.androidovshchik.base.dialogs.RationaleDialog
import io.androidovshchik.base.dialogs.RationaleSupportDialog

object PermissionUtil {

    private const val REQUEST_PERMISSIONS = 2018

    fun allPermissions(context: Context): Array<String> {
        return context.packageManager.getPackageInfo(context.packageName,
            PackageManager.GET_PERMISSIONS).requestedPermissions ?: arrayOf()
    }

    fun isGranted(context: Context, permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || PackageManager.PERMISSION_GRANTED ==
            ContextCompat.checkSelfPermission(context, permission)
    }

    fun areGranted(context: Context, vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (!isGranted(context, permission)) {
                return false
            }
        }
        return true
    }

    @Suppress("DEPRECATION")
    fun request(activity: Activity, finishActivity: Boolean, vararg permissions: String) {
        for (permission in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                if (activity is AppCompatActivity) {
                    RationaleSupportDialog.newInstance(finishActivity)
                        .show(activity.supportFragmentManager, "dialog")
                } else {
                    RationaleDialog.newInstance(finishActivity)
                        .show(activity.fragmentManager, "dialog")
                }
                return
            }
        }
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_PERMISSIONS)
    }
}