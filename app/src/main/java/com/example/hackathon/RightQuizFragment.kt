package com.example.hackathon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon.databinding.FragmentRightQuizBinding
import com.example.hackathon.databinding.FragmentWrongQuizBinding

class RightQuizFragment : Fragment() {
    private lateinit var binding: FragmentRightQuizBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRightQuizBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}