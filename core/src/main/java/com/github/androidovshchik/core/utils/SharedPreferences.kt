@file:Suppress("unused")

package com.github.androidovshchik.core.utils

import android.content.SharedPreferences

fun SharedPreferences.getString(name: String): String {
    return getString(name, "").trim()
}

fun <T> SharedPreferences.getString(name: String, defaultValue: T): String {
    return getString(name, defaultValue?.toString())!!.trim()
}

fun SharedPreferences.getBoolean(name: String): Boolean {
    return getBoolean(name, false)
}

fun SharedPreferences.getInt(name: String): Int {
    return getInt(name, 0)
}

fun SharedPreferences.getLong(name: String): Long {
    return getLong(name, 0L)
}

fun <T> SharedPreferences.putString(name: String, value: T) {
    edit().putString(name, value?.toString())
        .apply()
}

fun SharedPreferences.putBoolean(name: String, value: Boolean) {
    edit().putBoolean(name, value)
        .apply()
}

fun SharedPreferences.putInt(name: String, value: Int) {
    edit().putInt(name, value)
        .apply()
}

fun SharedPreferences.putLong(name: String, value: Long) {
    edit().putLong(name, value)
        .apply()
}

fun SharedPreferences.has(name: String): Boolean {
    return contains(name)
}

fun SharedPreferences.remove(name: String) {
    if (has(name)) {
        edit().remove(name)
            .apply()
    }
}

fun SharedPreferences.clear() {
    edit().clear()
        .apply()
}

fun SharedPreferences.getAllSorted(): List<Pair<String, *>> {
    return all.toSortedMap(compareBy<String> { it.length }
        .thenBy { it })
        .toList()
}