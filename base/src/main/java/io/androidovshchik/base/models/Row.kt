@file:Suppress("unused")

package io.androidovshchik.base.models

import android.content.ContentValues
import android.database.Cursor
import java.util.ArrayList

abstract class Row {

    var rowId = NONE

    abstract val table: String

    abstract fun toContentValues(): ContentValues

    abstract fun parseCursor(cursor: Cursor)

    companion object {

        const val COLUMN_ROW_ID = "rowid"

        const val NONE = 0L

        @Throws(Exception::class)
        fun <T : Row> getRows(cursor: Cursor?, rowClass: Class<T>): ArrayList<T> {
            val rows = ArrayList<T>()
            if (cursor == null) {
                return rows
            }
            cursor.use { _ ->
                while (cursor.moveToNext()) {
                    val row = rowClass.newInstance()
                    row.parseCursor(cursor)
                    rows.add(row)
                }
            }
            return rows
        }
    }
}