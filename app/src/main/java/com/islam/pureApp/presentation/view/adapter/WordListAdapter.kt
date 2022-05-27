package com.islam.pureApp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.pureApp.databinding.OneItemWordBinding
import com.islam.pureApp.domain.entites.Word

class WordListAdapter :
    ListAdapter<Word, WordListAdapter.MainViewHolder>(WordDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MainViewHolder(private val binding: OneItemWordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): MainViewHolder {
                val binding = OneItemWordBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return MainViewHolder(binding)
            }
        }

        fun bind(item: Word) {
            binding.wordText.text = item.text
            binding.repetitionsCount.text = item.count.toString()
        }
    }

}
