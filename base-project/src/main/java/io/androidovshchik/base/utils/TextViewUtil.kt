@file:Suppress("unused")

package io.androidovshchik.base.utils

import android.text.InputFilter
import android.widget.TextView
import android.text.method.DigitsKeyListener
import android.text.InputType

object TextViewUtil {

    fun maxLength(textView: TextView, maxLength: Int) {
        val array = arrayOfNulls<InputFilter>(1)
        array[0] = InputFilter.LengthFilter(maxLength)
        textView.filters = array
    }

    fun onlyNumbers(textView: TextView) {
        textView.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or
            InputType.TYPE_NUMBER_FLAG_SIGNED
        textView.keyListener = DigitsKeyListener.getInstance("0123456789")
    }
}
