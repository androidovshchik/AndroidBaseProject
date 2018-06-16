@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package io.androidovshchik.base.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
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

    fun request(activity: Activity, vararg permissions: String): Observable<Boolean> {
        return Observable.create { emitter: ObservableEmitter<Boolean> ->
            if (emitter.isDisposed) {
                return@create
            }
            val resultListener = object : MultiplePermissionsListener {

                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    emitter.onNext(report.areAllPermissionsGranted())
                    emitter.onComplete()
                }

                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>,
                                                                token: PermissionToken) {
                    token.continuePermissionRequest()
                }
            }
            val dialogListener = DialogOnAnyDeniedMultiplePermissionsListener.Builder
                .withContext(activity)
                .withTitle(R.string.permission_title)
                .withMessage(R.string.permission_message)
                .withButtonText(android.R.string.ok)
                .build()
            val listeners = CompositeMultiplePermissionsListener(resultListener, dialogListener)
            Dexter.withActivity(activity)
                .withPermissions(*permissions)
                .withListener(listeners)
                .onSameThread()
                .check()
        }
    }
}