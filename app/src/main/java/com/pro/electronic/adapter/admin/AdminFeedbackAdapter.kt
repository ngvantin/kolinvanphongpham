package com.pro.electronic.adapter.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pro.electronic.R
import com.pro.electronic.adapter.admin.AdminFeedbackAdapter.AdminFeedbackViewHolder
import com.pro.electronic.model.Feedback

class AdminFeedbackAdapter(private val mListFeedback: List<Feedback>?) :
    RecyclerView.Adapter<AdminFeedbackViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminFeedbackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_feedback, parent, false)
        return AdminFeedbackViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdminFeedbackViewHolder, position: Int) {
        val feedback = mListFeedback!![position]
        holder.tvEmail.text = feedback.email
        holder.tvFeedback.text = feedback.comment
    }

    override fun getItemCount(): Int {
        if (mListFeedback != null) {
            return mListFeedback.size
        }
        return 0
    }

    class AdminFeedbackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEmail: TextView = itemView.findViewById(R.id.tv_email)
        val tvFeedback: TextView = itemView.findViewById(R.id.tv_feedback)
    }
}
