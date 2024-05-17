package com.example.hackathon

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        val loginInfo = arguments?.getParcelable<LoginData>("loginInfo")

        if (loginInfo != null) {
            binding.tvName.setText(loginInfo.nickname + "님,")
            binding.tvLevel.setText("Level " + loginInfo.level.toString())
            binding.prg.progress = loginInfo.exp / 5
        }

        return binding.root
    }
}