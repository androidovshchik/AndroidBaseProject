package io.androidovshchik.demo.screens

import android.annotation.SuppressLint
import android.os.Bundle
import com.github.androidovshchik.core.utils.context.appContext
import com.github.androidovshchik.sqlbrite.SQLBriteManager
import com.github.androidovshchik.sqlbrite.utils.getRows
import com.github.androidovshchik.support.BaseV7Activity
import com.google.gson.GsonBuilder
import io.androidovshchik.demo.R
import io.androidovshchik.demo.models.Version
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_sqlite.*

class SQLBriteActivity : BaseV7Activity() {

    private val sqlBriteManager = SQLBriteManager()

    private val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    private var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        title = javaClass.simpleName
        sqlBriteManager.openAssetsDb(appContext, "demo.db", 1)
        loadVersions()
        add.setOnClickListener { _ ->
            sqlBriteManager.onInsertRow(Version("LOLLIPOP", 21))
                .subscribe {
                    loadVersions()
                }
        }
        remove.setOnClickListener { _ ->
            index++
            sqlBriteManager.onDeleteRow(Version(index - 1L))
                .subscribe {
                    loadVersions()
                }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadVersions() {
        sqlBriteManager.onSelectTable("SELECT rowid, * from versions")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val rows = it.getRows(Version::class.java)
                json.text = gson.toJson(rows)
                if (rows.size == 0) {
                    index = 1
                }
                remove.text = "remove at $index"
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        sqlBriteManager.closeDb()
    }
}
