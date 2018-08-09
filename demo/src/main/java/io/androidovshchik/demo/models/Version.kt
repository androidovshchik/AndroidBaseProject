@file:Suppress("unused")

package io.androidovshchik.demo.models

import android.content.ContentValues
import android.database.Cursor
import com.github.androidovshchik.sqlbrite.COLUMN_ROW_ID
import com.github.androidovshchik.sqlbrite.models.Row

class Version : Row {

    override val table = "versions"

    constructor() : super(0L)

    constructor(rowId: Long) : super(rowId)

    constructor(name: String, code: Int) : super(0L) {
        this.name = name
        this.code = code
    }

    var name: String = ""

    var code: Int = 0

    override fun toContentValues(): ContentValues {
        val values = ContentValues()
        if (rowId > 0) {
            values.put(COLUMN_ROW_ID, rowId)
        }
        values.put("name", name)
        values.put("code", code)
        return values
    }

    override fun parseCursor(cursor: Cursor) {
        rowId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ROW_ID))
        name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
        code = cursor.getInt(cursor.getColumnIndexOrThrow("code"))
    }
}