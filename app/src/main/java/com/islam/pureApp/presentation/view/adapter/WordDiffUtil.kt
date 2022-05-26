package com.islam.pureApp.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.islam.pureApp.domain.entites.Word

class WordDiffUtil : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }
}