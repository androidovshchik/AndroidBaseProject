@file:Suppress("unused")

package com.github.androidovshchik.support

import com.github.androidovshchik.core.BaseApplication

open class BaseSApplication : BaseApplication() {

    override fun initACRA() {
        initACRA(R.style.Library_Support_Dialog, "vladkalyuzhnyu@gmail.com")
    }

    override fun initACRA(replyEmail: String) {
        initACRA(R.style.Library_Support_Dialog, replyEmail)
    }
}