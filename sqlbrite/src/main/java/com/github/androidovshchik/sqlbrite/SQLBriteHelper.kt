package com.github.androidovshchik.sqlbrite

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.content.Context
import android.os.Build
import com.github.androidovshchik.core.utils.copyFromAssets
import com.github.androidovshchik.core.utils.sep
import java.io.File

open class SQLBriteHelper(version: Int, private val dbName: String) : SupportSQLiteOpenHelper.Callback(version) {

    override fun onCreate(db: SupportSQLiteDatabase) {}

    override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    open fun openAssetsDb(context: Context) {
        val file = context.getDatabasePath(dbName)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val path = File("${context.applicationInfo.dataDir}${sep}databases$sep")
            if (!path.exists()) {
                path.mkdir()
            }
        }
        if (!file.exists()) {
            file.copyFromAssets(context, dbName)
        }
    }
}