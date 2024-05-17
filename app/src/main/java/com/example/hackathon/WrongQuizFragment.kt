package com.example.hackathon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon.databinding.FragmentWrongQuizBinding

class WrongQuizFragment : Fragment() {
    private lateinit var binding: FragmentWrongQuizBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWrongQuizBinding.inflate(layoutInflater, container, false)

        binding.ex.text = arguments?.getString("ex")

        return binding.root
    }

}