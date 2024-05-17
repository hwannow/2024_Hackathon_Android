package com.example.hackathon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.databinding.LeftchattingSampleBinding
import com.example.hackathon.databinding.RightchattingSampleBinding

class ChattingAdapter(val chattingList: MutableList<ChatData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val binding = LeftchattingSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LeftChattingViewHolder(binding)
        }
        else {
            val binding = RightchattingSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            RightChattingViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return chattingList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LeftChattingViewHolder) {
            holder.chat.text = chattingList[position].chat
        }
        else if (holder is RightChattingViewHolder) {
            holder.chat.text = chattingList[position].chat
        }
    }
    override fun getItemViewType(position: Int): Int {
        return chattingList[position].viewType
    }
}