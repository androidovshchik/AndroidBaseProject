@file:Suppress("unused")

package com.github.androidovshchik.utils

import android.text.InputFilter
import android.widget.TextView
import android.text.method.DigitsKeyListener
import android.text.InputType

fun TextView.maxLength(maxLength: Int) {
    val array = arrayOfNulls<InputFilter>(1)
    array[0] = InputFilter.LengthFilter(maxLength)
    filters = array
}

fun TextView.onlyNumbers() {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or
        InputType.TYPE_NUMBER_FLAG_SIGNED
    keyListener = DigitsKeyListener.getInstance("0123456789")
}
