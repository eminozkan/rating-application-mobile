package dev.ozkan.ratingapplication.app.home.comment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.ozkan.ratingapplication.R
import dev.ozkan.ratingapplication.app.home.models.Comments
import dev.ozkan.ratingapplication.app.home.product.ProductActivity
import dev.ozkan.ratingapplication.core.auth.Session
import dev.ozkan.ratingapplication.core.home.selected.SelectedItem
import dev.ozkan.ratingapplication.core.retrofit.MainRetrofitInstance

class CommentRecyclerViewAdapter(private val comments: ArrayList<Comments>) :
    RecyclerView.Adapter<CommentRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentRecyclerViewAdapter.MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentRecyclerViewAdapter.MyViewHolder, position: Int) {
        val currentItem = comments[position]
        holder.commentText.text = currentItem.commentText
        holder.usageTime.text = getUsageTimeBySimpleText(currentItem.usageTime)
        holder.ratingText.text = "★ ${getRatingBySingleDigit(currentItem.rating)}/5"
    }

    private fun getUsageTimeBySimpleText(usageTime: String): String {
        return when (usageTime) {
            "LESS_THAN_THREE_MONTHS" -> "0-3 Months"
            "THREE_TO_SIX_MONTHS" -> "3-6 Months"
            "SIX_TO_TWELVE_MONTHS" -> "6-12 Months"
            "OVER_TWELVE_MONTHS" -> "12+ Months"
            else -> "0"
        }
    }

    private fun getRatingBySingleDigit(rating: String): String {
        return when (rating) {
            "ONE" -> "1"
            "TWO" -> "2"
            "THREE" -> "3"
            "FOUR" -> "4"
            "FIVE" -> "5"
            else -> "0"
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val commentText: TextView = itemView.findViewById(R.id.commentText)
        val ratingText: TextView = itemView.findViewById(R.id.rating_text)
        val usageTime: TextView = itemView.findViewById(R.id.usageTime)
    }
}