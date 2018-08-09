@file:Suppress("unused")

package com.github.androidovshchik.sqlite.models

import android.content.ContentValues
import android.database.Cursor

abstract class Row(var rowId: Long) {

    abstract val table: String

    abstract fun toContentValues(): ContentValues

    abstract fun parseCursor(cursor: Cursor)
}