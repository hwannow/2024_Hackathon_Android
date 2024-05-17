package com.example.hackathon

import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.databinding.LeftchattingSampleBinding

class LeftChattingViewHolder(binding: LeftchattingSampleBinding): RecyclerView.ViewHolder(binding.root) {
    val chat = binding.tvText
}