@file:Suppress("DEPRECATION")

package io.androidovshchik.demo.screens

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import com.github.androidovshchik.core.utils.newProgressDialog
import com.github.androidovshchik.support.BaseV7Activity
import io.androidovshchik.demo.R
import kotlinx.android.synthetic.main.activity_progress_dialog.*

class ProgressDialogActivity : BaseV7Activity() {

    private var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_dialog)
        title = javaClass.simpleName
        show.setOnClickListener { _ ->
            dialog = newProgressDialog
            Handler().postDelayed({
                if (!isFinishing) {
                    dialog?.dismiss()
                }
            }, 3000)
        }
    }
}
