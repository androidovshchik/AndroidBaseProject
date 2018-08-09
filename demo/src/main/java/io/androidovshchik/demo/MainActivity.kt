package io.androidovshchik.demo

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.github.androidovshchik.core.utils.context.appContext
import com.github.androidovshchik.support.BaseV7Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseV7Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.addItemDecoration(DividerItemDecoration(appContext, LinearLayoutManager.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ScreensAdapter()
    }
}
