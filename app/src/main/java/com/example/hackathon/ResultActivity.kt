package com.example.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import com.example.hackathon.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var cnt = intent.getIntExtra("correctCnt", 0)
        cnt *= 10

        binding.tvInc?.setText("+"+cnt.toString())

        binding.btnDone.setOnClickListener {
            finish()
        }
    }
}