package com.thebobighat.emp

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class AdapterNewTasks(private var orders: List<GetOrderResponsePayloadItem>, private var fragmentManager: FragmentManager) : RecyclerView.Adapter<AdapterNewTasks.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_my_task, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.tv_orderid_value).text = orders[position]._id
            findViewById<TextView>(R.id.tv_createdat_value).text = orders[position].createdAt
            findViewById<TextView>(R.id.tv_custname_value).text = orders[position].customer_name
            findViewById<TextView>(R.id.tv_custaddr_value).text = orders[position].address

            val api = RetrofitInstance.getInstance().create(TdgApi::class.java)
            val sharedPreferences: SharedPreferences? = context.getSharedPreferences("TDG_APP_ODN", Context.MODE_PRIVATE)

            findViewById<Button>(R.id.btn_assign_pickup).setOnClickListener {
                
            }
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}