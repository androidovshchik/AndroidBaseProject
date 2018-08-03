package io.github.androidovshchik.demo

import android.annotation.SuppressLint
import android.os.Bundle
import com.github.androidovshchik.BaseV7Activity
import com.github.androidovshchik.utils.allPermissions
import com.github.androidovshchik.utils.request
import timber.log.Timber

class MainActivity : BaseV7Activity() {

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        request(*allPermissions)
            .subscribe({

            }, {
                Timber.e(it)
            })
    }
}
