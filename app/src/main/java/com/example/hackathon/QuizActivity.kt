package com.example.hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hackathon.databinding.ActivityQuizBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizActivity : AppCompatActivity(), QuizFragment.OnQuestionAnsweredListener {
    private lateinit var binding: ActivityQuizBinding
    private var correctCnt = 0
    private var currentQuestionIndex = 0
    private lateinit var quizdata : QuizData

    private val answer = intArrayOf(1, 2, 3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            showQuestionFragment()
        }

    }
    override fun onQuestionAnswered(checkedId: Int, explain: String) {
        val fragment = if (checkedId == 1) {
            correctCnt++
            RightQuizFragment()
        } else {
            WrongQuizFragment()
        }
        var bund = Bundle()
        if (checkedId == 0) {
            bund.putString("ex", explain)
        }
        fragment.arguments = bund

        supportFragmentManager.beginTransaction()
            .replace(R.id.fg, fragment)
            .commit()

        Handler(Looper.getMainLooper()).postDelayed({
            currentQuestionIndex++
            if (currentQuestionIndex < 3) {
                showQuestionFragment()
            } else {
                val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                intent.putExtra("correctCnt", correctCnt)
                startActivity(intent)

               val intent2 = Intent(this@QuizActivity, MainPage::class.java)
                intent2.putExtra("correctCnt", correctCnt)
                setResult(RESULT_OK, intent2)
                finish()
            }
        }, 2000) // 2초 동안 결과 화면 표시
    }

    private fun showQuestionFragment() {
        RetrofitClient.instance.getQuiz().enqueue(object: Callback<QuizData> {
            override fun onResponse(
                call: Call<QuizData>,
                response: Response<QuizData>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    var bundle = Bundle()
                    bundle.putParcelable("quizInfo", response.body())
                    val fragment = QuizFragment()
                    fragment.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fg, fragment)
                        .commit()
                } else {
                    Toast.makeText(this@QuizActivity, "퀴즈 생성 실패1", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QuizData>, t: Throwable) {
                Toast.makeText(this@QuizActivity, "퀴즈 생성 실패2", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
