package io.androidovshchik.demo

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.androidovshchik.core.utils.context.appContext
import com.github.androidovshchik.core.utils.context.newIntent
import com.github.androidovshchik.support.BaseV7Activity
import io.androidovshchik.demo.screens.PermissionsActivity
import io.androidovshchik.demo.screens.ProgressDialogActivity
import io.androidovshchik.demo.screens.RandomActivity
import io.androidovshchik.demo.screens.SqliteActivity
import java.util.*

class ScreensAdapter : RecyclerView.Adapter<ScreensAdapter.ViewHolder>() {

    var screens: ArrayList<Class<out BaseV7Activity>> = arrayListOf(
        PermissionsActivity::class.java,
        ProgressDialogActivity::class.java,
        RandomActivity::class.java,
        SqliteActivity::class.java
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_screen,
            parent, false), viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val screen = screens[position]
        holder.caption.text = screen.simpleName
    }

    override fun getItemCount(): Int {
        return screens.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @Suppress("UNUSED_PARAMETER")
    inner class ViewHolder(itemView: View, position: Int) : RecyclerView.ViewHolder(itemView) {

        var caption: TextView = itemView.findViewById(R.id.caption)

        init {
            itemView.setOnClickListener {
                val context = itemView.context.appContext
                val intent = context.newIntent(screens[adapterPosition])
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }
}
