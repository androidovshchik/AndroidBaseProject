package io.androidovshchik.demo.screens

import android.annotation.SuppressLint
import android.os.Bundle
import com.github.androidovshchik.core.utils.context.appContext
import com.github.androidovshchik.sqlite.SqliteManager
import com.github.androidovshchik.sqlite.utils.getRows
import com.github.androidovshchik.support.BaseV7Activity
import com.google.gson.GsonBuilder
import io.androidovshchik.demo.R
import io.androidovshchik.demo.models.Version
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_sqlite.*

class SqliteActivity : BaseV7Activity() {

    private val sqliteManager = SqliteManager()

    private val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    private var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        title = javaClass.simpleName
        sqliteManager.openAssetsDb(appContext, "demo.db", 1)
        loadVersions()
        add.setOnClickListener { _ ->
            sqliteManager.onInsertRow(Version("LOLLIPOP", 21))
                .subscribe {
                    loadVersions()
                }
        }
        remove.setOnClickListener { _ ->
            index++
            sqliteManager.onDeleteRow(Version(index - 1L))
                .subscribe {
                    loadVersions()
                }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadVersions() {
        sqliteManager.onSelectTable("SELECT rowid, * from versions")
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
        sqliteManager.closeDb()
    }
}
