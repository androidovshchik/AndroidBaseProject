package io.androidovshchik.demo

import android.annotation.SuppressLint
import android.os.Bundle
import com.github.androidovshchik.BaseAppCompatActivity
import com.github.androidovshchik.utils.PermissionUtil
import timber.log.Timber

class MainActivity : BaseAppCompatActivity() {

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermissionUtil.request(this, *PermissionUtil.allPermissions(applicationContext))
            .subscribe {
                Timber.d("GRANTED: $it")
            }
    }
}
