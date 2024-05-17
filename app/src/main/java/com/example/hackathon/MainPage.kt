package com.example.hackathon

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.hackathon.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class MainPage : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var req: UpdateData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent  = intent
        val loginInfo = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("loginInfo", LoginData::class.java)
        } else {
            intent.getParcelableExtra("loginInfo") as? LoginData
        }
        val email = intent.getStringExtra("email")


        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val correctCnt = it.data?.getIntExtra("correctCnt", 0)
                    if (email != null && correctCnt != null) {
                        req = UpdateData(email, correctCnt)
                    }

                    if (req != null) {
                        RetrofitClient.instance.updateExp(req).enqueue(object: Callback<LoginData> {
                            override fun onResponse(
                                call: Call<LoginData>,
                                response: Response<LoginData>
                            ) {
                                if (response.isSuccessful && response.body() != null) {
                                    var bundle = Bundle()
                                    bundle.putParcelable("loginInfo", response.body())
                                    val fragment = MainFragment()
                                    fragment.arguments = bundle
                                    supportFragmentManager.beginTransaction()
                                        .replace(R.id.fg, fragment)
                                        .commit()
                                } else {
                                    Toast.makeText(this@MainPage, "업데이트 실패1", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                                Toast.makeText(this@MainPage, "업데이트 실패2", Toast.LENGTH_SHORT).show()
                            }

                        })
                    }
                }
            }


        val mainFragment = MainFragment()
        var bundle = Bundle()
        bundle.putParcelable("loginInfo", loginInfo as LoginData)
        mainFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fg, mainFragment).commit()

        binding.btnChat.setOnClickListener{
            toChat()
        }
        binding.btnQuiz.setOnClickListener{
            toQuiz()
        }
        binding.btnEmotion.setOnClickListener{
            toEmotion()
        }

    }

    fun toChat() {
        val intent = Intent(this@MainPage, ChattingActivity::class.java)
        startActivity(intent)
    }

    fun toQuiz() {
        val intent = Intent(this@MainPage, QuizActivity::class.java)
        resultLauncher.launch(intent)
    }

    fun toEmotion() {
        val intent = Intent(this@MainPage, EmotionActivity::class.java)
        resultLauncher.launch(intent)
    }
}