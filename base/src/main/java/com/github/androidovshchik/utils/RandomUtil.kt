@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.github.androidovshchik.utils

import java.util.*

object RandomUtil {

    const val numbers = "0123456789"

    const val upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    const val lowerLetters = "abcdefghijklmnopqrstuvwxyz"

    const val allLetters = upperLetters + lowerLetters

    const val allChars = numbers + allLetters

    fun nextInt(range: IntRange): Int {
        return range.start + Random().nextInt(range.last - range.start)
    }

    fun nextIntInclusive(range: IntRange): Int {
        return range.start + Random().nextInt(range.endInclusive - range.start)
    }

    fun nextString(range: IntRange, chars: String): String {
        var output = ""
        val size = nextIntInclusive(range)
        for (i in 0 until size) {
            output += chars[Math.floor(Math.random() * chars.length).toInt()]
        }
        return output
    }
}