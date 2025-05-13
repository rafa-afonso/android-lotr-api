package com.example.androidtheoneapi.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtheoneapi.R
import com.example.androidtheoneapi.databinding.FragmentMovieItemBinding
import com.example.androidtheoneapi.model.response.MovieResponse

/**
 * [RecyclerView.Adapter] that can display a [MovieListFragment].
 */
class MovieListRecyclerViewAdapter :
    RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<MovieResponse>() {
        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.movieName.text = item.name

        val context = holder.rtScore.context
        val rottenTomatoesScore = Math.round(item.rottenTomatoesScore)
        holder.rtScore.text =
            context.getString(R.string.rottenTomatoesScore, rottenTomatoesScore.toString())
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(binding: FragmentMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieName: TextView = binding.movieName
        val rtScore: TextView = binding.rtScore

        override fun toString(): String {
            return super.toString() + " '" + rtScore.text + "'"
        }
    }

}