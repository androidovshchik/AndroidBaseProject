package com.github.androidovshchik.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.content.Context
import timber.log.Timber
import java.io.FileOutputStream
import java.io.IOException

open class DbCallback(val version: Int, private val dbName: String): SupportSQLiteOpenHelper.Callback(version) {

    override fun onCreate(db: SupportSQLiteDatabase) {}

    override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun openAssetsDatabase(context: Context) {
        val file = context.getDatabasePath(dbName)
        if (!file.exists()) {
            try {
                copyDatabaseFromAssets(context)
            } catch (e: IOException) {
                Timber.e(e)
            }
        }
    }

    @Throws(IOException::class)
    private fun copyDatabaseFromAssets(context: Context) {
        val input = context.assets.open(dbName)
        val output = FileOutputStream(context.getDatabasePath(dbName))
        input.use { _ ->
            output.use { _ ->
                input.copyTo(output)
            }
        }
    }
}