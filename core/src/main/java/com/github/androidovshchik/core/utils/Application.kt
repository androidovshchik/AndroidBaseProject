@file:Suppress("unused")

package com.github.androidovshchik.core.utils

import android.app.Application
import android.support.annotation.StyleRes
import com.github.androidovshchik.core.R
import org.acra.ACRA
import org.acra.ReportField
import org.acra.ReportingInteractionMode
import org.acra.config.ConfigurationBuilder

fun Application.setupACRA(@StyleRes theme: Int, replyEmail: String) {
    ACRA.init(this, ConfigurationBuilder(this)
        .setMailTo(replyEmail)
        .setReportingInteractionMode(ReportingInteractionMode.DIALOG)
        .setResDialogTheme(theme)
        .setResDialogText(R.string.error_crash)
        .setResDialogCommentPrompt(R.string.error_comment)
        .setCustomReportContent(
            ReportField.APP_VERSION_CODE,
            ReportField.APP_VERSION_NAME,
            ReportField.ANDROID_VERSION,
            ReportField.PHONE_MODEL,
            ReportField.BRAND,
            ReportField.PRODUCT,
            ReportField.USER_COMMENT,
            ReportField.USER_APP_START_DATE,
            ReportField.USER_CRASH_DATE,
            ReportField.STACK_TRACE,
            ReportField.LOGCAT
        ))
}