package io.androidovshchik.demoapp

import android.os.Bundle
import android.util.Log
import io.androidovshchik.base.BaseAppCompatActivity
import io.androidovshchik.base.utils.PermissionUtil

class MainActivity : BaseAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Log.d("sa", PermissionsUtil.getPermissions(applicationContext).toString())
        PermissionUtil.request(applicationContext, *PermissionUtil.allPermissions(applicationContext))
            .subscribe {
                Log.d("it", "RESULT " + it)
            }
    }
}
