@file:Suppress("unused")

package com.github.androidovshchik.sqlbrite.utils

import android.database.Cursor
import com.github.androidovshchik.sqlbrite.models.Row

@Throws(Exception::class)
fun <R : Row> Cursor.getRows(rowClass: Class<R>): ArrayList<R> {
    val rows = ArrayList<R>()
    use {
        while (moveToNext()) {
            val row = rowClass.newInstance()
            row.parseCursor(this)
            rows.add(row)
        }
    }
    return rows
}