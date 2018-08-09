@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.github.androidovshchik.sqlite

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.github.androidovshchik.sqlite.models.Row
import com.squareup.sqlbrite3.BriteDatabase
import com.squareup.sqlbrite3.SqlBrite
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

open class SqliteManager {

    var db: BriteDatabase? = null

    fun openAssetsDb(context: Context, dbName: String, version: Int) {
        closeDb()
        val dbCallback = SqliteOpenHelper(version, dbName)
        dbCallback.openAssetsDatabase(context)
        db = SqlBrite.Builder()
            .build()
            .wrapDatabaseHelper(FrameworkSQLiteOpenHelperFactory()
                .create(SupportSQLiteOpenHelper.Configuration
                    .builder(context)
                    .name(dbName)
                    .callback(dbCallback)
                    .build()), Schedulers.io())
    }

    fun onExecSql(sql: String): Observable<Boolean> {
        return Observable.create { emitter: ObservableEmitter<Boolean> ->
            if (emitter.isDisposed) {
                return@create
            }
            val transaction = db!!.newTransaction()
            try {
                db!!.execute(sql)
                transaction.markSuccessful()
            } finally {
                transaction.end()
            }
            emitter.onNext(true)
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())
    }

    fun onSelectTable(sql: String): Observable<Cursor> {
        return Observable.create(ObservableOnSubscribe<Cursor> { emitter ->
            if (emitter.isDisposed) {
                return@ObservableOnSubscribe
            }
            val cursor: Cursor?
            val transaction = db!!.newTransaction()
            try {
                cursor = db!!.query(sql)
                transaction.markSuccessful()
            } finally {
                transaction.end()
            }
            if (cursor != null) {
                emitter.onNext(cursor)
            }
            emitter.onComplete()
        }).subscribeOn(Schedulers.io())
    }

    fun onInsertRow(row: Row): Observable<Row> {
        return Observable.create { emitter: ObservableEmitter<Row> ->
            if (emitter.isDisposed) {
                return@create
            }
            val transaction = db!!.newTransaction()
            try {
                row.rowId = insertRow(row)
                transaction.markSuccessful()
            } finally {
                transaction.end()
            }
            emitter.onNext(row)
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())
    }

    fun insertRow(row: Row): Long {
        return db!!.insert(row.table, SQLiteDatabase.CONFLICT_REPLACE, row.toContentValues())
    }

    fun onUpdateRow(row: Row): Observable<Int> {
        return Observable.create { emitter: ObservableEmitter<Int> ->
            if (emitter.isDisposed) {
                return@create
            }
            val result: Int
            val transaction = db!!.newTransaction()
            try {
                result = updateRow(row)
                transaction.markSuccessful()
            } finally {
                transaction.end()
            }
            emitter.onNext(result)
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())
    }

    fun updateRow(row: Row): Int {
        return db!!.update(row.table, SQLiteDatabase.CONFLICT_IGNORE, row.toContentValues(),
            "rowid=?", row.rowId.toString())
    }

    fun onDeleteRow(row: Row): Observable<Int> {
        return Observable.create { emitter: ObservableEmitter<Int> ->
            if (emitter.isDisposed) {
                return@create
            }
            val result: Int
            val transaction = db!!.newTransaction()
            try {
                result = deleteRow(row)
                transaction.markSuccessful()
            } finally {
                transaction.end()
            }
            emitter.onNext(result)
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())
    }

    fun deleteRow(row: Row): Int {
        return db!!.delete(row.table, "rowid=?", row.rowId.toString())
    }

    fun clearTable(table: String): Int {
        return db!!.delete(table, null, null)
    }

    fun isDbClosed(): Boolean {
        return db == null
    }

    fun closeDb() {
        if (!isDbClosed()) {
            db!!.close()
            db = null
        }
    }
}
