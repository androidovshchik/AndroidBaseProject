@file:Suppress("unused")

package io.androidovshchik.base.utils

import android.app.Application
import io.androidovshchik.base.R
import org.acra.ACRA
import org.acra.ReportField
import org.acra.ReportingInteractionMode
import org.acra.config.ConfigurationBuilder

object ACRAUtil {

    fun init(application: Application) {
        ACRA.init(application, ConfigurationBuilder(application)
            .setMailTo("vladkalyuzhnyu@gmail.com")
            .setReportingInteractionMode(ReportingInteractionMode.DIALOG)
            .setResDialogTheme(R.style.AppTheme_Dialog)
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
}
