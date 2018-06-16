@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package io.androidovshchik.base.utils

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.os.Build
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import io.androidovshchik.base.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

object PermissionUtil {

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

    fun request(context: Context, vararg permissions: String): Observable<Boolean> {
        return Observable.create { emitter: ObservableEmitter<Boolean> ->
            if (emitter.isDisposed) {
                return@create
            }
            val title = context.getString(R.string.settings_title)
            Permissions.check(context, permissions, null,
                Permissions.Options().setSettingsDialogMessage(context.getString(R.string.settings_message))
                    .setSettingsText(context.getString(R.string.settings))
                    .setSettingsDialogTitle(title)
                    .setRationaleDialogTitle(title), object : PermissionHandler() {

                override fun onGranted() {
                    emitter.onNext(true)
                    emitter.onComplete()
                }

                override fun onBlocked(context: Context?, blockedList: ArrayList<String>?): Boolean {
                    onDenied(context, blockedList)
                    return false
                }

                override fun onDenied(context: Context?, deniedPermissions: ArrayList<String>?) {
                    emitter.onNext(false)
                    emitter.onComplete()
                }
            })
        }
    }
}