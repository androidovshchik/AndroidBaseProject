package io.androidovshchik.demo

import android.annotation.SuppressLint
import android.os.Bundle
import io.androidovshchik.base.BaseAppCompatActivity
import io.androidovshchik.base.utils.PermissionUtil

class MainActivity : BaseAppCompatActivity() {

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermissionUtil.request(this, *PermissionUtil.allPermissions(applicationContext))
    }
}
