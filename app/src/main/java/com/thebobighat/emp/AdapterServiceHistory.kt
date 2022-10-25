package com.thebobighat.emp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class AdapterServiceHistory(private var tasks: List<String>, private var fragmentManager: FragmentManager) : RecyclerView.Adapter<AdapterServiceHistory.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_service_history, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.tv_taskid).text = tasks[position]
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}