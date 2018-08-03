@file:Suppress("unused", "DEPRECATION")

package com.github.androidovshchik.utils

import android.app.ProgressDialog
import com.github.androidovshchik.BaseV7Activity
import com.github.androidovshchik.R
import com.gun0912.tedpermission.TedPermissionResult
import com.tedpark.tedpermission.rx2.TedRx2Permission
import io.reactivex.Single

val BaseV7Activity.newProgressDialog: ProgressDialog get() =
    ProgressDialog.show(this, getString(R.string.wait), null, true)

fun BaseV7Activity.request(vararg permissions: String): Single<TedPermissionResult> {
    val builder = TedRx2Permission.with(this)
    builder.setPermissions(*permissions)
    builder.setRationaleTitle(R.string.permission_title)
    builder.setRationaleMessage(R.string.permission_message)
    builder.setRationaleConfirmText(R.string.permission_confirm)
    builder.setDeniedTitle(R.string.permission_title)
    builder.setDeniedMessage(R.string.permission_message)
    builder.setDeniedCloseButtonText(R.string.permission_close)
    builder.setGotoSettingButtonText(R.string.permission_setting)
    return builder.request()
}