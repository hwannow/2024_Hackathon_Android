package com.example.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hackathon.databinding.ActivityMainBinding

class MainPage : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private fun changeFragment(fragment: Fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fg, fragment)
            .commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quizFragment = QuizFragment()
        val todoFragment = TodoFragment()
        val chatFragment = ChatFragment()
        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fg, quizFragment).commit()


        binding.bottomnav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.quiz ->supportFragmentManager.beginTransaction().replace(R.id.fg, quizFragment).commit()
                R.id.todo ->supportFragmentManager.beginTransaction().replace(R.id.fg, todoFragment).commit()
                R.id.chat ->supportFragmentManager.beginTransaction().replace(R.id.fg, chatFragment).commit()
                R.id.mypage -> supportFragmentManager.beginTransaction().replace(R.id.fg, mainFragment).commit()
            }
            return@setOnItemSelectedListener true
        }

    }
}