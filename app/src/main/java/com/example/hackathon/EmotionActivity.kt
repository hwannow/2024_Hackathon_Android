package com.example.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.hackathon.databinding.ActivityEmotionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmotionActivity : AppCompatActivity(), EmotionFragment.OnQuestionAnsweredListener {
    private lateinit var binding: ActivityEmotionBinding
    private var correctCnt = 0
    private var currentQuestionIndex = 0
    private var URL = "str"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmotionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            showQuestionFragment()
        }

    }
    override fun onQuestionAnswered(checked: Int, explain: String) {
        val fragment = if (checked == 1) {
            correctCnt++
            RightEmotionFragment()
        } else {
            WrongEmotionFragment()
        }
        var bund = Bundle()
        bund.putString("ex", explain)
        fragment.arguments = bund
        supportFragmentManager.beginTransaction()
            .replace(R.id.fg, fragment)
            .commit()

        Handler(Looper.getMainLooper()).postDelayed({
            currentQuestionIndex++
            if (currentQuestionIndex < 3) {
                showQuestionFragment()
            } else {
                val intent = Intent(this@EmotionActivity, ResultEmotionActivity::class.java)
                intent.putExtra("correctCnt", correctCnt)
                startActivity(intent)

                val intent2 = Intent(this@EmotionActivity, MainPage::class.java)
                intent2.putExtra("correctCnt", correctCnt)
                setResult(RESULT_OK, intent2)
                finish()
            }
        }, 2000) // 2초 동안 결과 화면 표시
    }

    private fun showQuestionFragment() {
        RetrofitClient.instance.getEmotion().enqueue(object: Callback<EmotionRequestData> {
            override fun onResponse(
                call: Call<EmotionRequestData>,
                response: Response<EmotionRequestData>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    var bundle = Bundle()
                    URL = response.body()!!.imageURL
                    bundle.putString("emotionInfo", URL)
                    val fragment = EmotionFragment()
                    fragment.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fg, fragment)
                        .commit()
                }
                else {
                    Toast.makeText(this@EmotionActivity, "퀴즈 생성 실패1", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<EmotionRequestData>, t: Throwable) {
                Toast.makeText(this@EmotionActivity, "퀴즈 생성 실패2", Toast.LENGTH_SHORT).show()
            }

        })
    }
}