package com.example.test

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


data class BlogItem (
    val title: String,
    val content: Any,
    var isExpanded : Boolean

)


class BlogAdapter(private val blogItems: List<BlogItem>) : RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blog_item, parent, false)
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blogItem = blogItems[position]
        holder.title.text = blogItem.title
        holder.content.text = blogItem.content.toString() // Assuming content is a String

        holder.content.visibility = if (blogItem.isExpanded) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            blogItem.isExpanded = !blogItem.isExpanded // Toggle the expanded state
            notifyItemChanged(position) // Notify the adapter to refresh the item
        }
    }

    override fun getItemCount(): Int {
        return blogItems.size
    }

    class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val content: TextView = itemView.findViewById(R.id.content)
    }
}
