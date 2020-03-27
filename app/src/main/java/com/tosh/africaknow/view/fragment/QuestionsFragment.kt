package com.tosh.africaknow.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tosh.africaknow.R
import com.tosh.africaknow.utils.addScorePreferences
import com.tosh.africaknow.utils.addToSharedPreferences
import com.tosh.africaknow.utils.getSharedPreferencesValue
import com.tosh.africaknow.view.adapter.QuestionAdapter
import com.tosh.africaknow.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_questions.*


class QuestionsFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel
    lateinit var adapter:QuestionAdapter
    private var num:Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        score.text = "SCORE: ${getSharedPreferencesValue(activity!!, "SCORE")}"


        val result = getSharedPreferencesValue(activity!!, "NUM")

        initQuestion(result)
    }

    fun initQuestion(questionNum: Int){
        val layoutManager = LinearLayoutManager(activity)
        answerRv!!.layoutManager = layoutManager

        mainViewModel.fetchQuestions(questionNum).observe(this, Observer {
            textQuestion.text = it.question

            answerRv.adapter = QuestionAdapter(it.answers){answer->
                Log.e("VALUE", " $answer")
                answer.forEach{
                    if (it.value == true){
                        val initialScore = getSharedPreferencesValue(activity!!, "SCORE")
                        val questionPos = getSharedPreferencesValue(activity!!, "NUM")
                        addScorePreferences(activity!!, initialScore.plus(1))
                        addToSharedPreferences(activity!!, questionPos.plus(1))
                        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
                        ft.detach(this).attach(this).commit()
                    }else if(it.value == false){
                        val initialScore = getSharedPreferencesValue(activity!!, "SCORE")
                        val questionPos = getSharedPreferencesValue(activity!!, "NUM")
                        addScorePreferences(activity!!, initialScore.minus(1))

                        val finalScore = getSharedPreferencesValue(activity!!, "SCORE")
                        if (finalScore < 0  ){
                            val gameOverFragment = GameOverFragment()
                            val transaction = activity!!.supportFragmentManager.beginTransaction()
                            transaction.replace(R.id.mainFragment, gameOverFragment)
                            transaction.commit()
                        }else{
                            addToSharedPreferences(activity!!, questionPos.plus(1))
                            val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
                            ft.detach(this).attach(this).commit()
                        }


                    }
                }
            }
        })
    }


}
