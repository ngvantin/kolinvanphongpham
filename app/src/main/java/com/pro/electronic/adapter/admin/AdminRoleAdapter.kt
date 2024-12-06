package com.pro.electronic.adapter.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pro.electronic.R
import com.pro.electronic.adapter.admin.AdminRoleAdapter.AdminRoleViewHolder
import com.pro.electronic.model.Admin

class AdminRoleAdapter(private val mListAdmin: List<Admin>?) :
    RecyclerView.Adapter<AdminRoleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminRoleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_role, parent, false)
        return AdminRoleViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdminRoleViewHolder, position: Int) {
        val admin = mListAdmin!![position]
        holder.tvEmail.text = admin.email
    }

    override fun getItemCount(): Int {
        if (mListAdmin != null) {
            return mListAdmin.size
        }
        return 0
    }

    class AdminRoleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEmail: TextView = itemView.findViewById(R.id.tv_email)
    }
}
