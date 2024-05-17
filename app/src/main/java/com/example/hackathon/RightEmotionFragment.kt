package com.example.hackathon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon.databinding.FragmentEmotionBinding
import com.example.hackathon.databinding.FragmentRightEmotionBinding

class RightEmotionFragment : Fragment() {
    private lateinit var binding: FragmentRightEmotionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRightEmotionBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}