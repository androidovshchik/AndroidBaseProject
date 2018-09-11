package io.androidovshchik.demo.screens

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.github.androidovshchik.core.CHARS
import com.github.androidovshchik.core.NUMBERS
import com.github.androidovshchik.core.utils.nextInt
import com.github.androidovshchik.core.utils.nextString
import com.github.androidovshchik.support.BaseV7Activity
import io.androidovshchik.demo.R
import kotlinx.android.synthetic.main.activity_random.*
import java.util.*

class RandomActivity : BaseV7Activity() {

    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        title = javaClass.simpleName
        int03.setOnClickListener {
            showFastToast(random.nextInt(0..3).toString())
        }
        string88.setOnClickListener {
            Toast.makeText(applicationContext, random.nextString(8..8, CHARS), Toast.LENGTH_LONG)
                .show()
        }
        string23.setOnClickListener {
            showFastToast(random.nextString(2..3, NUMBERS))
        }
    }

    private fun showFastToast(message: String) {
        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
        toast.show()
        Handler().postDelayed({
            toast.cancel()
        }, 500)
    }
}
