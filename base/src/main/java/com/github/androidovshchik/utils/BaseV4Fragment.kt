@file:Suppress("unused")

package com.github.androidovshchik.utils

import com.github.androidovshchik.BaseV4Fragment
import com.gun0912.tedpermission.TedPermission

fun BaseV4Fragment.openAppSettings() {
    TedPermission.startSettingActivityForResult(this)
}