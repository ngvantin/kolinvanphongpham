package com.pro.electronic.adapter.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pro.electronic.R
import com.pro.electronic.adapter.admin.AdminRevenueAdapter.RevenueViewHolder
import com.pro.electronic.model.Order
import com.pro.electronic.utils.Constant
import com.pro.electronic.utils.DateTimeUtils.convertTimeStampToDate2

class AdminRevenueAdapter(private val mListOrder: List<Order>?) :
    RecyclerView.Adapter<RevenueViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevenueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_revenue, parent, false)
        return RevenueViewHolder(view)
    }

    override fun onBindViewHolder(holder: RevenueViewHolder, position: Int) {
        val order = mListOrder!![position]
        holder.tvId.text = order.id.toString()
        holder.tvDate.text = convertTimeStampToDate2(order.id)

        val strAmount = order.total.toString() + Constant.CURRENCY
        holder.tvTotalAmount.text = strAmount
    }

    override fun getItemCount(): Int {
        if (mListOrder != null) {
            return mListOrder.size
        }
        return 0
    }

    class RevenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tv_id)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        val tvTotalAmount: TextView = itemView.findViewById(R.id.tv_total_amount)
    }
}