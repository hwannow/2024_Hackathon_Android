package com.example.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.databinding.ActivityChattingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChattingActivity : AppCompatActivity() {
    private lateinit var binding:ActivityChattingBinding
    var chattingList: MutableList<ChatData> = mutableListOf()
    val chattingAdapter by lazy { ChattingAdapter(chattingList) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.adapter = chattingAdapter
        binding.rv.layoutManager = LinearLayoutManager(this@ChattingActivity, LinearLayoutManager.VERTICAL, false)

        binding.btnChat.setOnClickListener {
            val input = binding.etInput.text.toString()
            if (input.isEmpty()) {
                Toast.makeText(this@ChattingActivity, "메시지를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                chattingList.add(ChatData(input, 1))
                binding.rv.adapter?.notifyItemInserted(chattingList.size)
                binding.etInput.setText("")
                binding.rv.scrollToPosition(chattingList.size - 1)
                val body = JoinData(input)
                RetrofitClient.instance.chat(body).enqueue(object: Callback<ChattingData> {
                    override fun onResponse(
                        call: Call<ChattingData>,
                        response: Response<ChattingData>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            chattingList.add(ChatData(response.body()!!.response, 0))
                            binding.rv.adapter?.notifyItemInserted(chattingList.size)
                            binding.rv.scrollToPosition(chattingList.size - 1)
                        } else if (response.body() == null) {
                            Toast.makeText(this@ChattingActivity, "채팅 실패3", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(this@ChattingActivity, "채팅 실패1", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ChattingData>, t: Throwable) {
                        Toast.makeText(this@ChattingActivity, "채팅 실패2", Toast.LENGTH_SHORT).show()
                    }
                })

            }
        }

        binding.btnBack.setOnClickListener{
            finish()
        }

    }

}