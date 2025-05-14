package com.example.androidtheoneapi.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtheoneapi.databinding.FragmentCharacterItemBinding
import com.example.androidtheoneapi.model.response.CharacterResponse

/**
 * [RecyclerView.Adapter] that can display a [CharacterListFragment].
 */
class CharacterListRecyclerViewAdapter :
    RecyclerView.Adapter<CharacterListRecyclerViewAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<CharacterResponse>() {
        override fun areItemsTheSame(
            oldItem: CharacterResponse,
            newItem: CharacterResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterResponse,
            newItem: CharacterResponse
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentCharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.name.text = item.name
        holder.race.text = item.race

        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(item)
                }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(binding: FragmentCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.characterName
        val race: TextView = binding.characterRace

        override fun toString(): String {
            return super.toString() + " '" + name.text + " " + race.text + "'"
        }
    }

    private var onItemClickListener: ((CharacterResponse) -> Unit)? = null

    fun setOnItemClickListener(listener: (CharacterResponse) -> Unit) {
        onItemClickListener = listener
    }

}