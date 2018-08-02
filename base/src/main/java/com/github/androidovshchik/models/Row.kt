@file:Suppress("unused")

package com.github.androidovshchik.models

import android.content.ContentValues
import android.database.Cursor

abstract class Row {

    var rowId = 0L

    abstract val table: String

    abstract fun toContentValues(): ContentValues

    abstract fun parseCursor(cursor: Cursor)
}