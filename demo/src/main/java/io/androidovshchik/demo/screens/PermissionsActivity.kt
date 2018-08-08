package io.androidovshchik.demo.screens

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import com.github.androidovshchik.BaseV7Activity
import com.github.androidovshchik.utils.*
import io.androidovshchik.demo.R
import kotlinx.android.synthetic.main.activity_permissions.*
import timber.log.Timber

class PermissionsActivity : BaseV7Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)
        title = javaClass.simpleName
        all.setOnClickListener { _ ->
            request(*allPermissions)
                .subscribe({
                    Timber.d("allPermissions isGranted ${it.isGranted}")
                    // it.deniedPermissions is nullable
                    Timber.d("deniedPermissions ${it.deniedPermissions}")
                    if (it.isGranted) {
                        // success
                    }
                }, {
                    Timber.e(it)
                })
        }
        write.setOnClickListener { _ ->
            request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe({
                    Timber.d("WRITE_EXTERNAL_STORAGE isGranted ${it.isGranted}")
                    // it.deniedPermissions is nullable
                    Timber.d("deniedPermissions ${it.deniedPermissions}")
                    if (it.isGranted) {
                        // success
                    }
                }, {
                    Timber.e(it)
                })
        }
        camera.setOnClickListener { _ ->
            request(Manifest.permission.CAMERA)
                .subscribe({
                    Timber.d("CAMERA isGranted ${it.isGranted}")
                    // it.deniedPermissions is nullable
                    Timber.d("deniedPermissions ${it.deniedPermissions}")
                    if (it.isGranted) {
                        // success
                    }
                }, {
                    Timber.e(it)
                })
        }
        areAllGranted.setOnClickListener {
            Toast.makeText(appContext, "Granted: ${areGranted(*allPermissions)}", Toast.LENGTH_SHORT)
                .show()
        }
        isWriteGranted.setOnClickListener {
            Toast.makeText(appContext, "Granted: ${isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)}", Toast.LENGTH_SHORT)
                .show()
        }
        isCameraGranted.setOnClickListener {
            Toast.makeText(appContext, "Granted: ${isGranted(Manifest.permission.CAMERA)}", Toast.LENGTH_SHORT)
                .show()
        }
        isCameraDenied.setOnClickListener {
            Toast.makeText(appContext, "Denied: ${isDenied(Manifest.permission.CAMERA)}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
