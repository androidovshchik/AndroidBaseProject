package io.androidovshchik.demo

import com.github.androidovshchik.support.BaseSApplication

@Suppress("ConstantConditionIf", "unused")
class MainApplication : BaseSApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            initTimber()
            initACRA()
        }
    }
}
