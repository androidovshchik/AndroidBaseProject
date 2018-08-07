@file:Suppress("unused", "DEPRECATION")

package com.github.androidovshchik.core.utils

import android.app.Activity
import android.app.ProgressDialog
import com.github.androidovshchik.R
import com.gun0912.tedpermission.TedPermissionResult
import com.tedpark.tedpermission.rx2.TedRx2Permission
import io.reactivex.Single

val Activity.newProgressDialog: ProgressDialog get() =
    ProgressDialog.show(this, getString(R.string.wait), null, true)

fun Activity.request(vararg permissions: String): Single<TedPermissionResult> {
    val builder = TedRx2Permission.with(this)
    builder.setPermissions(*permissions)
    builder.setDeniedTitle(R.string.permission_title)
    builder.setDeniedMessage(R.string.permission_message)
    builder.setDeniedCloseButtonText(R.string.permission_close)
    builder.setGotoSettingButtonText(R.string.permission_setting)
    return builder.request()
}