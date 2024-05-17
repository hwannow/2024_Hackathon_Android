package com.example.hackathon

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.hackathon.databinding.FragmentEmotionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmotionFragment : Fragment() {
    private lateinit var binding: FragmentEmotionBinding
    private var listener: OnQuestionAnsweredListener? = null
    interface OnQuestionAnsweredListener {
        fun onQuestionAnswered(checked: Int, explain: String)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnQuestionAnsweredListener) {
            listener = context
        } else {
            return
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentEmotionBinding.inflate(layoutInflater, container, false)
        val URL = arguments?.getString("emotionInfo")

        Glide.with(this).load(URL).into(binding.img)

        binding.rg.setOnCheckedChangeListener{ group, checkedId ->
            var ans = ""
            when(checkedId){
                R.id.rb_one -> ans = binding.rbOne.text.toString()
                R.id.rb_two ->  ans = binding.rbTwo.text.toString()
                R.id.rb_three -> ans = binding.rbThree.text.toString()
            }
            RetrofitClient.instance.postEmotion(ans).enqueue(object:
                Callback<EmotionPostData> {
                override fun onResponse(
                    call: Call<EmotionPostData>,
                    response: Response<EmotionPostData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        if (response.body()!!.correct) {
                            listener?.onQuestionAnswered(1, "")
                        }
                        else {
                            listener?.onQuestionAnswered(0, response.body()!!.explanation)
                        }
                    }
                    else {
                        Toast.makeText(activity, "채점 실패1", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<EmotionPostData>, t: Throwable) {
                    Toast.makeText(activity, "채점 실패2", Toast.LENGTH_SHORT).show()
                }

            })
        }

        return binding.root
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}