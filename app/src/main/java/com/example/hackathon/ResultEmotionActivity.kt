package com.example.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hackathon.databinding.ActivityResultEmotionBinding

class ResultEmotionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultEmotionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultEmotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cnt = intent.getIntExtra("correctCnt", 0)
        cnt *= 10

        binding.tvInc?.setText("+"+cnt.toString())

        binding.btnDone.setOnClickListener {
            finish()
        }
    }
}