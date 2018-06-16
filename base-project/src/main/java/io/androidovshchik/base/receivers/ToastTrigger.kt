package io.androidovshchik.base.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ToastTrigger : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.hasExtra(EXTRA_MESSAGE)) {
            Toast.makeText(context, intent.getStringExtra(EXTRA_MESSAGE), Toast.LENGTH_SHORT)
                .show()
        }
    }

    companion object {

        const val EXTRA_MESSAGE = "message"
    }
}