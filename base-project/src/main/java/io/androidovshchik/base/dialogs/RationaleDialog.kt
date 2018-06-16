@file:Suppress("DEPRECATION")

package io.androidovshchik.base.dialogs

import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import io.androidovshchik.base.R

class RationaleDialog : DialogFragment() {

    private var finishActivity: Boolean = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        finishActivity = arguments!!.getBoolean(ARGUMENT_FINISH_ACTIVITY)
        return AlertDialog.Builder(activity!!)
            .setCancelable(false)
            .setTitle(getString(R.string.permission_title))
            .setMessage(getString(R.string.permission_message))
            .setPositiveButton(getString(R.string.permission_button)) { _: DialogInterface, _: Int ->

            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (finishActivity) {
            activity!!.finish()
        }
    }

    companion object {

        private const val ARGUMENT_FINISH_ACTIVITY = "finish"

        fun newInstance(finishActivity: Boolean): RationaleDialog {
            val dialog = RationaleDialog()
            val arguments = Bundle()
            arguments.putBoolean(ARGUMENT_FINISH_ACTIVITY, finishActivity)
            dialog.arguments = arguments
            return dialog
        }
    }
}