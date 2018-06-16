package io.androidovshchik.demoapp

import android.os.Bundle
import android.util.Log
import io.androidovshchik.baseproject.BaseAppCompatActivity
import io.androidovshchik.baseproject.utils.PermissionsUtil

class MainActivity : BaseAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Log.d("sa", PermissionsUtil.getPermissions(applicationContext).toString())
        PermissionsUtil.request(applicationContext, *PermissionsUtil.allPermissions(applicationContext))
            .subscribe {
                Log.d("it", "RESULT " + it)
            }
    }
}
