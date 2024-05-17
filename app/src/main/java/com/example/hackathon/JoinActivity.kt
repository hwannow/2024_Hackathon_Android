package com.example.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hackathon.databinding.ActivityJoinBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJoinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btJoin.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val password = binding.etPw.text.toString()
            val passwordCheck = binding.etPwCk.text.toString()
            val nickname = binding.etNickname.text.toString()
            val body = JoinRequest(nickname, password, passwordCheck, email)

            RetrofitClient.instance.join(body).enqueue(object: Callback<JoinData> {
                override fun onResponse(
                    call: Call<JoinData>,
                    response: Response<JoinData>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@JoinActivity, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@JoinActivity, "회원가입 실패1", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<JoinData>, t: Throwable) {
                    Toast.makeText(this@JoinActivity, "회원가입 실패2", Toast.LENGTH_SHORT).show()
                }

            })
        }

        binding.btnBack.setOnClickListener{
            finish()
        }
    }
}