package io.androidovshchik.demo

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.androidovshchik.BaseV7Activity
import com.github.androidovshchik.utils.appContext
import com.github.androidovshchik.utils.newIntent
import io.androidovshchik.demo.screens.PermissionsActivity
import java.util.*

class ScreensAdapter : RecyclerView.Adapter<ScreensAdapter.ViewHolder>() {

    var screens: ArrayList<Class<out BaseV7Activity>> = arrayListOf(
        PermissionsActivity::class.java
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
