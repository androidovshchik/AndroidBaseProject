package io.androidovshchik.demoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import io.androidovshchik.base.BaseAppCompatActivity
import io.androidovshchik.base.utils.PermissionUtil

class MainActivity : BaseAppCompatActivity() {

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermissionUtil.request(applicationContext, *PermissionUtil.allPermissions(applicationContext))
            .subscribe {
                Log.d("Permissions", "GRANTED $it")
            }
    }
}
