package com.example.hackathon

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private var listener: OnQuestionAnsweredListener? = null
    var answer = 0
    var expl = ""
    interface OnQuestionAnsweredListener {
        fun onQuestionAnswered(checkedId: Int, explain: String)
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
        binding = FragmentQuizBinding.inflate(layoutInflater, container, false)

        val questionText = arguments?.getParcelable<QuizData>("quizInfo")
        if (questionText != null) {
            binding.tvQuestion.text = questionText.quizTitle
            binding.rbOne.text = questionText.quizChoice1
            binding.rbTwo.text = questionText.quizChoice2
            binding.rbThree.text = questionText.quizChoice3
            answer = questionText.answer
            expl = questionText.explanation
        }

        binding.rg.setOnCheckedChangeListener{ group, checkedId ->
            var i = 0
            when(checkedId){
                R.id.rb_one -> {
                    if (answer == 1)
                        i = 1
                }
                R.id.rb_two ->  {
                    if (answer == 2)
                        i = 1
                }
                R.id.rb_three -> {
                    if (answer == 3)
                        i = 1
                }
            }
            listener?.onQuestionAnswered(i, expl)
        }

        return binding.root
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}