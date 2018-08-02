@file:Suppress("unused")

package com.github.androidovshchik.utils

import android.view.inputmethod.InputMethodManager

fun InputMethodManager.showKeyboard() {
    toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun InputMethodManager.hideKeyboard() {
    toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
}