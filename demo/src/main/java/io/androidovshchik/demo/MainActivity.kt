package io.androidovshchik.demo

import android.os.Bundle
import com.github.androidovshchik.BaseV7Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseV7Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ScreensAdapter()
    }
}
