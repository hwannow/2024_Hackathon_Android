package com.example.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hackathon.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener {
            val email = binding.etId.text.toString()
            val password = binding.etPw.text.toString()
            val body = mapOf(
                "email" to email,
                "password" to password
            )

            RetrofitClient.instance.login(body).enqueue(object: Callback<LoginData> {
                override fun onResponse(
                    call: Call<LoginData>,
                    response: Response<LoginData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val intent = Intent(this@Login, MainPage::class.java)
                        intent.putExtra("loginInfo", response.body())
                        intent.putExtra("email", email)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@Login, "로그인 실패1", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginData>, t: Throwable) {
                    Toast.makeText(this@Login, "로그인 실패2", Toast.LENGTH_SHORT).show()
                }

            })
        }

        binding.btnJoin.setOnClickListener {
            val intent = Intent(this@Login, JoinActivity::class.java)
            startActivity(intent)
        }

    }
}