package io.androidovshchik.base.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

@Suppress("MemberVisibilityCanBePrivate", "unused")
class Preferences(context: Context) {

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun getString(name: String): String {
        return preferences.getString(name, "")!!.trim()
    }

    fun <T> getString(name: String, def: T): String {
        return preferences.getString(name, toString(def))!!.trim()
    }

    fun getBoolean(name: String): Boolean {
        return preferences.getBoolean(name, false)
    }

    fun getBoolean(name: String, def: Boolean): Boolean {
        return preferences.getBoolean(name, def)
    }

    fun getInt(name: String): Int {
        return preferences.getInt(name, 0)
    }

    fun getInt(name: String, def: Int): Int {
        return preferences.getInt(name, def)
    }

    fun getLong(name: String): Long {
        return preferences.getLong(name, 0L)
    }

    fun getLong(name: String, def: Long): Long {
        return preferences.getLong(name, def)
    }

    fun <T> putString(name: String, value: T) {
        preferences.edit().putString(name, toString(value)).apply()
    }

    fun putBoolean(name: String, value: Boolean) {
        preferences.edit().putBoolean(name, value).apply()
    }

    fun putInt(name: String, value: Int) {
        preferences.edit().putInt(name, value).apply()
    }

    fun putLong(name: String, value: Long) {
        preferences.edit().putLong(name, value).apply()
    }

    fun has(name: String): Boolean {
        return preferences.contains(name)
    }

    fun remove(name: String) {
        if (has(name)) {
            preferences.edit().remove(name)
                .apply()
        }
    }

    fun clear() {
        preferences.edit().clear()
            .apply()
    }

    private fun <T> toString(value: T): String {
        return if (String::class.java.isInstance(value)) (value as String).trim() else value.toString()
    }
}
