package com.example.movieapp.presentation.home

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.domain.model.MinMovie

class MovieDiffUtils : DiffUtil.ItemCallback<MinMovie>() {
    override fun areItemsTheSame(oldItem: MinMovie, newItem: MinMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MinMovie, newItem: MinMovie): Boolean {
        return oldItem == newItem
    }
}