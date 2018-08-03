package com.github.androidovshchik.sqlite

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.content.Context
import com.github.androidovshchik.utils.copyFileFromAssets

open class SqliteCallback(val version: Int, private val dbName: String) : SupportSQLiteOpenHelper.Callback(version) {

    override fun onCreate(db: SupportSQLiteDatabase) {}

    override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun openAssetsDatabase(context: Context) {
        val file = context.getDatabasePath(dbName)
        if (!file.exists()) {
            context.getDatabasePath(dbName)
                .copyFileFromAssets(context, dbName)
        }
    }
}