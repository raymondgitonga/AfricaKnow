package com.tosh.africaknow.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.tosh.africaknow.R
import com.tosh.africaknow.utils.addScorePreferences
import com.tosh.africaknow.utils.addToSharedPreferences
import com.tosh.africaknow.utils.getSharedPreferencesValue
import com.tosh.africaknow.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_game_start.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameStart : Fragment() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        addInitialQuestionCount()
        initQuestionsFragment()
    }

    fun initQuestionsFragment(){
        btnPlay.setOnClickListener {
            val questionsFragment =
                QuestionsFragment()
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFragment, questionsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    fun addInitialQuestionCount(){
        addToSharedPreferences(activity!!,0)
        addScorePreferences(activity!!, 0)
    }
}
