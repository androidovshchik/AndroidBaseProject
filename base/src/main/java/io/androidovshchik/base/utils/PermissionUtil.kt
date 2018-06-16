@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package io.androidovshchik.base.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import io.androidovshchik.base.R

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

    fun request(activity: Activity, vararg permissions: String) {
        Dexter.withActivity(activity)
            .withPermissions(*permissions)
            .withListener(DialogOnAnyDeniedMultiplePermissionsListener.Builder
                .withContext(activity.applicationContext)
                .withTitle(R.string.permission_title)
                .withMessage(R.string.permission_message)
                .withButtonText(R.string.permission_button)
                .build())
            .onSameThread()
            .check()
    }
}