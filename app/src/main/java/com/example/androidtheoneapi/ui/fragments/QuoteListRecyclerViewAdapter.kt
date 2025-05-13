package com.example.androidtheoneapi.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtheoneapi.databinding.FragmentQuoteItemBinding
import com.example.androidtheoneapi.model.response.QuoteResponse

/**
 * [RecyclerView.Adapter] that can display a [QuoteListFragment].
 */
class QuoteListRecyclerViewAdapter :
    RecyclerView.Adapter<QuoteListRecyclerViewAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<QuoteResponse>() {
        override fun areItemsTheSame(oldItem: QuoteResponse, newItem: QuoteResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuoteResponse, newItem: QuoteResponse): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentQuoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.dialog.text = item.dialog
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(binding: FragmentQuoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val dialog: TextView = binding.quoteDialog

        override fun toString(): String {
            return super.toString() + " '" + dialog.text + "'"
        }
    }

}