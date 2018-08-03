@file:Suppress("unused", "DEPRECATION")

package com.github.androidovshchik.utils

import android.app.ProgressDialog
import com.github.androidovshchik.BaseV7Activity
import com.github.androidovshchik.R
import com.gun0912.tedpermission.TedPermission
import com.gun0912.tedpermission.TedPermissionResult
import com.tedpark.tedpermission.rx2.TedRx2Permission
import io.reactivex.Single

val BaseV7Activity.newProgressDialog: ProgressDialog get() =
    ProgressDialog.show(this, getString(R.string.wait), null, true)

fun BaseV7Activity.request(vararg permissions: String): Single<TedPermissionResult> {
    val builder = TedRx2Permission.with(this)
    builder.setRationaleTitle(R.string.permission_title)
    builder.setRationaleMessage(R.string.permission_message)
    builder.setPermissions(*permissions)
    builder.request()
    return builder.request()
}

fun BaseV7Activity.openAppSettings() {
    TedPermission.startSettingActivityForResult(this)
}