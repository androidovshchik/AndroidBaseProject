package io.androidovshchik.demo.screens

import android.os.Bundle
import com.github.androidovshchik.support.BaseV7Activity
import io.androidovshchik.demo.R

class RandomActivity : BaseV7Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        title = javaClass.simpleName
    }
}
